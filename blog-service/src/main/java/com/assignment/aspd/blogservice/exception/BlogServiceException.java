package com.assignment.aspd.blogservice.exception;

public class BlogServiceException extends RuntimeException{
    public BlogServiceException(Throwable cause) {
        super(cause);
    }
    public BlogServiceException(String message) {
        super(message);
    }
}
