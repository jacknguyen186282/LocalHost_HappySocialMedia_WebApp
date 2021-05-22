package com.example.HappySocialMedia.mapper;

import com.example.HappySocialMedia.model.Comment;
import com.example.HappySocialMedia.model.Post;
import com.example.HappySocialMedia.model.User;
import com.example.HappySocialMedia.dto.CommentDto;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "commentDto.text", target = "text")
    @Mapping(source = "post", target = "post")
    @Mapping(source = "user", target = "user")
    @Mapping(expression = "java(java.time.Instant.now())", target = "createdDate")
    Comment map(CommentDto commentDto, Post post, User user);

    @Mapping(expression = "java(comment.getPost().getPostId())", target = "postId")
    @Mapping(expression = "java(comment.getUser().getUsername())", target = "userName")
    @Mapping(expression = "java(comment.getPost().getPostName())", target = "postName")
    @Mapping(expression = "java(comment.getPost().getDescription())", target = "description")
    CommentDto mapToDto(Comment comment);
}