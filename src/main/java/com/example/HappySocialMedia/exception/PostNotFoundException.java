package com.example.HappySocialMedia.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
