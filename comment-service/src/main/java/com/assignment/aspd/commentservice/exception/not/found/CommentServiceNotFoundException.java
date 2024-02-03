package com.assignment.aspd.commentservice.exception.not.found;

import com.assignment.aspd.commentservice.exception.CommentServiceException;

public abstract class CommentServiceNotFoundException extends CommentServiceException {
    public CommentServiceNotFoundException(Throwable cause) {
        super(cause);
    }

    public CommentServiceNotFoundException(String message) {
        super(message);
    }
}
