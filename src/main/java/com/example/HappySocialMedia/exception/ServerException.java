package com.example.HappySocialMedia.exception;

// This class will handle the serverException section, if the website meet errors when loading or retrieving key store, the website will display an error message
public class ServerException extends RuntimeException {
    public ServerException(String exceptionMessage, Exception exception) {
        super(exceptionMessage, exception);
    }

    public ServerException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
