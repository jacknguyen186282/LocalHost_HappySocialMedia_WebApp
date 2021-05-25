package com.example.HappySocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.HappySocialMedia.model.User;

@Repository
// Create a UserRepository interface to store user data and find user by userName
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
