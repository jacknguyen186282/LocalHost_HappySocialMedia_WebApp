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
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        postRepository.save(postMapper.map(postRequest, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
    
    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

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
