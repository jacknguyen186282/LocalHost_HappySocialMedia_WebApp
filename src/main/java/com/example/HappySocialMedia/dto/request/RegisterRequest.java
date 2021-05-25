package com.example.HappySocialMedia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

// Create a RegisterRequest containing only username, email and password
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    
}
