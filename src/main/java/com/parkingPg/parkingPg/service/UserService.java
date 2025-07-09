package com.parkingPg.parkingPg.service;


import com.parkingPg.parkingPg.dto.SignUpRequest;
import com.parkingPg.parkingPg.entity.EmailVerification;
import com.parkingPg.parkingPg.entity.Role;
import com.parkingPg.parkingPg.entity.User;
import com.parkingPg.parkingPg.entity.UserOption;
import com.parkingPg.parkingPg.repository.EmailVerificationRepo;
import com.parkingPg.parkingPg.repository.RoleRepository;
import com.parkingPg.parkingPg.repository.UserOptionRepo;
import com.parkingPg.parkingPg.repository.UserRepository;
import com.parkingPg.parkingPg.security.jwt.JwtUtils;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserService {

    @Value("${app.sendgrid.api-key}")
    private String sendgridApiKey;

    @Value("${app.sendgrid.from-email}")
    private String sendgridFromEmail;

    @Autowired
    private JavaMailSender mailSender;


    private final UserRepository userRepository;
    private final EmailVerificationRepo emailVerificationRepo;
    private final UserOptionRepo userOptionRepo;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserService(UserRepository userRepository, EmailVerificationRepo emailVerificationRepo, UserOptionRepo userOptionRepo, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.emailVerificationRepo = emailVerificationRepo;
        this.userOptionRepo = userOptionRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public String sendEmail(String to, String subject) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,false,"utf-8");
        int verificationCode = new Random().nextInt(1000,9000);
        mimeMessageHelper.setFrom(sendgridFromEmail);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(String.valueOf(verificationCode),false);
        mailSender.send(mimeMessage);
        return "success";
    }
    public EmailVerification sentVerificationCode(String email) throws IOException {


        Email from = new Email(sendgridFromEmail);
        Email to = new Email(email);
        Long verificationCode = ThreadLocalRandom.current().nextLong(1000,9000);
        String content = "<p>Your verification code is: " + verificationCode + "</p>";
        Content emailContent = new Content("text/html", content);
        Mail mail = new Mail(from, "Verification Code", to, emailContent);
        SendGrid sendGrid = new SendGrid(sendgridApiKey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        sendGrid.api(request);


        EmailVerification findUserByEmail = new EmailVerification(email,verificationCode,LocalDateTime.now().plusMinutes(10),false);
        emailVerificationRepo.saveAndFlush(findUserByEmail);
        return findUserByEmail;
    }

    public String signIn(String username, String password) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            UserOption findUserOption = userOptionRepo.findByUserId(userDetails.getId());

            if (findUserOption == null) {
                throw new RuntimeException("User not found");
            }

            if (findUserOption.getEmailVerification()) {
                sentVerificationCode(userDetails.getEmail());
                return "CODE REQUIRED";
            } else {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtUtils.generateJwtToken(userDetails.getUsername());
                return token;
            }

        } catch (AuthenticationException exception){
            throw new RuntimeException("Username or password incorrect");
        } catch (IOException e) {
            throw new RuntimeException("Failed to send verification code " + e);
        }
    }
        public User signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username is already in use");
        }
        Role findRole = roleRepository.findByid(1);
        Set<Role> roles = new HashSet<>();
        roles.add(findRole);
        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                roles
        );
        UserOption userOption = new UserOption(user,false);
        userRepository.saveAndFlush(user);
        userOptionRepo.save(userOption);
        return user;
    }
}
