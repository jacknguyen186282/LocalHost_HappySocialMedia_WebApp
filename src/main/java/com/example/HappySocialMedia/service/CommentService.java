package com.example.HappySocialMedia.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.example.HappySocialMedia.dto.CommentDto;
import com.example.HappySocialMedia.model.Comment;
import com.example.HappySocialMedia.model.Post;
import com.example.HappySocialMedia.model.User;
import com.example.HappySocialMedia.repository.CommentRepository;
import com.example.HappySocialMedia.repository.PostRepository;
import com.example.HappySocialMedia.repository.UserRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import com.example.HappySocialMedia.exception.PostNotFoundException;
import com.example.HappySocialMedia.mapper.CommentMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

//This is a service class that responsible to write logic related to the comment when the correct API is called
// from the CommentController
public class CommentService {
    private final AuthService authService;

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    // This method will the find the post id that user wants to comment
    // If it finds out the post, it will save content of comment along with that post id and user id to the database
    public void save(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

    }

    // This method will allow user to view all the comments in the specific post by its id
    public List<CommentDto> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    // This method will allow user to view all the comments of the user by the user name
    public List<CommentDto> getCommentsByUsername(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
