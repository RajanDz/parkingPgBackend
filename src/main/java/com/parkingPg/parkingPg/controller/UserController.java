package com.parkingPg.parkingPg.controller;

import com.parkingPg.parkingPg.dto.SignUpRequest;
import com.parkingPg.parkingPg.entity.User;
import com.parkingPg.parkingPg.service.UserDetailsImpl;
import com.parkingPg.parkingPg.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/signin")
    public ResponseEntity<String> signIn(@RequestParam String username, @RequestParam String password) {
            String userDetails = userService.signIn(username, password);
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
            User singUp = userService.signUp(signUpRequest);
            return ResponseEntity.ok(singUp);
    }
}
