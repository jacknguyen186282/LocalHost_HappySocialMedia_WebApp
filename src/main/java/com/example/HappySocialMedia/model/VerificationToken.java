package com.example.HappyTweet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
// Auto generate constructors for the model
@AllArgsConstructor
@NoArgsConstructor
@Entity
// Create a table in database with the name: "tokens"
@Table(name = "tokens")
public class VerificationToken {

    // Create VerificationToken containing variables such as id, token, user and expriryDate
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String token;
    @OneToOne(fetch = LAZY)
    private User user;
    private Instant expiryDate;
}
