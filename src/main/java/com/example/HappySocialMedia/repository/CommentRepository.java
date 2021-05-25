package com.example.HappySocialMedia.repository;

import com.example.HappySocialMedia.model.User;
import com.example.HappySocialMedia.model.Post;
import com.example.HappySocialMedia.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Define a class in a repostory layer that is responsible for storing and tracking the data
@Repository

// Create a commentRepository interface to store comment data and find comment by post or by user
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
