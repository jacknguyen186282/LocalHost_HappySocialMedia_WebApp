package com.example.HappySocialMedia.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

import com.example.HappySocialMedia.model.User;
import com.example.HappySocialMedia.repository.UserRepository;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor

//This class overrides the method loadUserByUsername()
public class UserDetailsServiceImplement implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username + "is invalid"));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), 
            user.getPassword(),
            user.isEnabled(),
            true, true, true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
