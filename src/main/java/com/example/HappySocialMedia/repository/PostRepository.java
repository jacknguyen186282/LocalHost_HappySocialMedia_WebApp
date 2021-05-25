package com.example.HappySocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.HappySocialMedia.model.Post;
import com.example.HappySocialMedia.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);
}
