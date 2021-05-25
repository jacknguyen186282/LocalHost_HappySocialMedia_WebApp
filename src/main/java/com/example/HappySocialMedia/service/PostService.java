package com.example.HappySocialMedia.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.example.HappySocialMedia.dto.request.PostRequest;
import com.example.HappySocialMedia.dto.response.PostResponse;
import com.example.HappySocialMedia.exception.PostNotFoundException;
import com.example.HappySocialMedia.mapper.PostMapper;
import com.example.HappySocialMedia.model.Post;
import com.example.HappySocialMedia.model.User;
import com.example.HappySocialMedia.repository.PostRepository;
import com.example.HappySocialMedia.repository.UserRepository;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
// Need slf4j to run Mapstruct
@Slf4j
@Transactional

//This is a service class that responsible to write logic related to the post when the correct API is called
// from the PostController
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    // This method will save the information to the database whenever the user create the post
    public void save(PostRequest postRequest) {
        postRepository.save(postMapper.map(postRequest, authService.getCurrentUser()));
        // The Mapper will take the info from the post Request dto and user and then assign it to each field in the post model
        // Finally save it to the database
    }

    // This method will get all post for user to view
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    // This method will allow user to view post by id
    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post); // The mapper will take information from Post and then assign to Post Response
    }

    // This method will allow user to view post by user name
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
