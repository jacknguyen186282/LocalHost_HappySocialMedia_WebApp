package com.example.HappyTweet.mapper;

import com.example.HappyTweet.dto.request.PostRequest;
import com.example.HappyTweet.dto.response.PostResponse;

import com.example.HappyTweet.model.Post;
import com.example.HappyTweet.model.User;
import com.example.HappyTweet.repository.CommentRepository;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {
    @Autowired
    private CommentRepository commentRepository;

    @Mapping(source = "postRequest.description", target = "description")
    @Mapping(source = "user", target = "user")
    @Mapping(expression = "java(java.time.Instant.now())", target = "createdDate")
    public abstract Post map(PostRequest postRequest, User user);

    @Mapping(source = "postId", target = "id")
    @Mapping(source = "user.username", target = "userName")
    @Mapping(expression = "java(commentCount(post))", target = "commentCount")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }
}