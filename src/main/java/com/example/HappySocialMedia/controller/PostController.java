package com.example.HappySocialMedia.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.HappySocialMedia.dto.request.PostRequest;
import com.example.HappySocialMedia.dto.response.PostResponse;
import com.example.HappySocialMedia.service.PostService;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor

//This class will implement and manage API to create and view posts for the user
public class PostController {

    private final PostService postService;

    // Create new post with POST method to save the information of a post to database
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
        // A status CREATED will be responded from server to client to confirm a post is created

    }

    // View all posts information with GET method
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

    // View post information by its id with GET method
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    // View posts information by user name with GET method
    @GetMapping("/by-username/{name}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String name) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(name));
    }

}
