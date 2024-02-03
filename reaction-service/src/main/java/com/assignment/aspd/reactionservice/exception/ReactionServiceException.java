package com.assignment.aspd.reactionservice.exception;

public class ReactionServiceException extends RuntimeException{
    public ReactionServiceException(Throwable cause) {
        super(cause);
    }
    public ReactionServiceException(String message) {
        super(message);
    }
}
