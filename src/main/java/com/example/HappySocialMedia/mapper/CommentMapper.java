package com.example.HappySocialMedia.mapper;

import com.example.HappySocialMedia.model.Comment;
import com.example.HappySocialMedia.model.Post;
import com.example.HappySocialMedia.model.User;
import com.example.HappySocialMedia.dto.CommentDto;

import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

// The mapper will support us passing data from dto to model and vice versa
@Mapper(componentModel = "spring")
public interface CommentMapper {

    // This will take the info from CommentDto, Post and User and then assign the data to Comment object before saving to database
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "commentDto.text", target = "text")
    @Mapping(source = "post", target = "post")
    @Mapping(source = "user", target = "user")
    @Mapping(expression = "java(java.time.Instant.now())", target = "createdDate")
    Comment map(CommentDto commentDto, Post post, User user);

    // This will take the info from Comment and then assign the data to CommentDto
    @Mapping(expression = "java(comment.getPost().getPostId())", target = "postId")
    @Mapping(expression = "java(comment.getUser().getUsername())", target = "userName")
    @Mapping(expression = "java(comment.getPost().getPostName())", target = "postName")
    @Mapping(expression = "java(comment.getPost().getDescription())", target = "description")
    CommentDto mapToDto(Comment comment);
}