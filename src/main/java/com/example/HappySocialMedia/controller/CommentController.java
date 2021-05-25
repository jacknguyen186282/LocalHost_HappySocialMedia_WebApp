package com.example.HappySocialMedia.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.HappySocialMedia.dto.CommentDto;
import com.example.HappySocialMedia.service.CommentService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor

//This class will implement and manage API to post and view comment of posts and users.
public class CommentController {
    private final CommentService commentService;

    //Create new comment with POST method to save to database
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return new ResponseEntity<>(CREATED);
        // A status CREATED will be responded from server to client to confirm comments is created
    }

    // View comment by post with GET method
    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK)
                .body(commentService.getCommentsByPost(postId));
    }

    // View comment by user name with GET method
    @GetMapping("/by-username/{userName}")
    public ResponseEntity<List<CommentDto>> getCommentsByUsername(@PathVariable String userName){
        return ResponseEntity.status(OK)
                .body(commentService.getCommentsByUsername(userName));
    }

}
