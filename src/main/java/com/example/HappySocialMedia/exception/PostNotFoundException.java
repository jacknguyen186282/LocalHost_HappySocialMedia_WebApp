package com.example.HappyTweet.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
