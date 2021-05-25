package com.example.HappySocialMedia.exception;

// This class will handle the founding post section, if the post is not found, the website will display a message error
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
