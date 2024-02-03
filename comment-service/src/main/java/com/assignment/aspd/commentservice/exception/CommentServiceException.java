package com.assignment.aspd.commentservice.exception;

public class CommentServiceException extends RuntimeException{
    public CommentServiceException(Throwable cause) {
        super(cause);
    }
    public CommentServiceException(String message) {
        super(message);
    }
}
