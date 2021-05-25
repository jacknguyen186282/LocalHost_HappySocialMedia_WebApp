package com.example.HappySocialMedia.service;

import com.example.HappySocialMedia.dto.request.LoginRequest;
import com.example.HappySocialMedia.dto.request.RegisterRequest;
import com.example.HappySocialMedia.dto.response.AuthenticationResponse;
import com.example.HappySocialMedia.exception.ServerException;
// import com.example.HappyTweet.model.Email;
import com.example.HappySocialMedia.model.User;
import com.example.HappySocialMedia.model.VerificationToken;
import com.example.HappySocialMedia.repository.UserRepository;
import com.example.HappySocialMedia.repository.VerificationTokenRepository;
import com.example.HappySocialMedia.security.JwtProvider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.UUID;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional

//This is a class that responsible to write logic related to the user when the correct API is called
// from the AuthController
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    // private final MailService mailService;

    // The register method will save the register information of the user to the database as well as generate token for them
    @Transactional
    public String register(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false); // At first when the user signs up the account of them is still not enabling,
                                // the user need to verify the token provided by server to enable the account can log in.

        userRepository.save(user);

        String token = generateVerificationToken(user);
        return token;
        // mailService.sendMail(new Email("Please Activate your Account",
        //         user.getEmail(), "Thank you for being a member of us. Please click on the url below to verify your account: " +
        //         "http://localhost:8080/api/auth/verification/" + token));
    }

    // This method will randomly create a Verification token via a link for users
    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    //This method will verify the token is correct or not
    public void verification(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new ServerException("Invalid Token. Please input valid token!")));
    }

    // This method will enable the account when the user verify the token
    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ServerException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    // This method will check the credential of information given by user when log in
    // If the information (user name and password) is correct, the application will allow you log in.
    public AuthenticationResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication); // Generate a jwt to authenticate the user
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }

    // This method will return the information of current user
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = 
        (org.springframework.security.core.userdetails.User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
        return userRepository
        .findByUsername(principal.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User name cannot be found: " + principal.getUsername()));
    }


}
