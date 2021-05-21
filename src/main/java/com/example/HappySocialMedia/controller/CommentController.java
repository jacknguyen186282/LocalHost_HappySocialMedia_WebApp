package com.example.HappyTweet.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.HappyTweet.dto.CommentDto;
import com.example.HappyTweet.service.CommentService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK)
                .body(commentService.getCommentsByPost(postId));
    }

    @GetMapping("/by-username/{userName}")
    public ResponseEntity<List<CommentDto>> getCommentsByUsername(@PathVariable String userName){
        return ResponseEntity.status(OK)
                .body(commentService.getCommentsByUsername(userName));
    }

}
