package com.parkingPg.parkingPg.controller;

import com.parkingPg.parkingPg.entity.EmailVerification;
import com.parkingPg.parkingPg.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/sendEmail/{to}/{subject}")
    public ResponseEntity<String> sendEmail(@PathVariable  String to, @PathVariable  String subject) throws MessagingException {
        String sendEmail = userService.sendEmail(to,subject);
        return ResponseEntity.ok(sendEmail);
    }
    @GetMapping("/sendCode/{email}")
    public ResponseEntity<EmailVerification> sendCode(@PathVariable String email) throws IOException {
        EmailVerification emailVerification = userService.sentVerificationCode(email);
        return ResponseEntity.ok(emailVerification);
    }
}
