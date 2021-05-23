package com.example.HappyTweet.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import com.example.HappyTweet.exception.ServerException;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import static io.jsonwebtoken.Jwts.parserBuilder;

@Service
public class JwtProvider {
    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException error) {
            throw new ServerException("There is an error while loading the key store", error);
        }

    }
    
    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException error) {
            throw new ServerException("There is an error while retrieving the key store", error);
        }
    }

    public String generateToken(Authentication authentication) {
        // A user can authenticate itself is a principal
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .compact();
    }
    
    /* 

        Authentication Filter Functions. Verifying by using public key

    */

    private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException error) {
            throw new ServerException("There is an error while retrieving the key store", error);
        }
    }

    // return true if jwt is valid after signing with public key
    public boolean validateToken(String jwt) {
        parserBuilder().setSigningKey(getPublickey()).build().parseClaimsJws(jwt);
        return true;
    }

    // get username from jwt to sign in
    public String getUsernameFromJwt(String token) {
        Claims claims = parserBuilder().setSigningKey(getPublickey()).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

