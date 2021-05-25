package com.example.HappySocialMedia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

// Create a LoginRequest where the information only contains username and password
public class LoginRequest {
    private String username;
    private String password;
}
