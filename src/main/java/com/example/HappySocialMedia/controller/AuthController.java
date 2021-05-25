package com.example.HappySocialMedia.controller;

import com.example.HappySocialMedia.dto.request.LoginRequest;
import com.example.HappySocialMedia.dto.request.RegisterRequest;
import com.example.HappySocialMedia.dto.response.AuthenticationResponse;
import com.example.HappySocialMedia.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        return "Congrats! Your registration is successful. Your token is " + authService.register(registerRequest);
        // return new ResponseEntity<>("Congrats! Your registration is successful. Your token is: ",
        // HttpStatus.OK);
    }

    @GetMapping("/verification/{token}")
    public ResponseEntity<String> verification(@PathVariable String token) {
        authService.verification(token);
        return new ResponseEntity<>("Verification is successful! Please go back your Home Page to enjoy!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }


}
