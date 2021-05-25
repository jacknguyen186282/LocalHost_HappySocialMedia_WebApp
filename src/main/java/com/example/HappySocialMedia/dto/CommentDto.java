package com.example.HappySocialMedia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor

// Create a CommentDto that stores variables such as id, postId, createdDate, text, username, postname and post description
public class CommentDto {
    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String userName;
    private String postName;
    // Post Description
    private String description;
}
