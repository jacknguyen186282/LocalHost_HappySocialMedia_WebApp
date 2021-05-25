package com.example.HappySocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.HappySocialMedia.model.Post;
import com.example.HappyTweet.model.User;

@Repository
// Create a PostRepository interface to store post data and find posts by user
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
