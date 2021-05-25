package com.example.HappySocialMedia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

// Create a PostRequest where the information only contains postID, postName and description
public class PostRequest {
    private Long postId;
    private String postName;
    private String description;
}
