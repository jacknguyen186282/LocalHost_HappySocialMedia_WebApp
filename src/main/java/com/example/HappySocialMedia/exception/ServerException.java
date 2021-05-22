package com.example.HappySocialMedia.exception;

public class ServerException extends RuntimeException {
    public ServerException(String exceptionMessage, Exception exception) {
        super(exceptionMessage, exception);
    }

    public ServerException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
