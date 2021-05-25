package com.example.HappySocialMedia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

// Create a DTO name PostResponse that contains id, postName, description, userName and commentCount
public class PostResponse {
    private Long id;
    private String postName;
    private String description;
    private String userName;
    private Integer commentCount;

}
