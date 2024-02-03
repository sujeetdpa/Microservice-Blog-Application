package com.assignment.aspd.commentservice.exception.not.found;

public class CommentNotFoundException extends CommentServiceNotFoundException{
    public CommentNotFoundException(Throwable cause) {
        super(cause);
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}
