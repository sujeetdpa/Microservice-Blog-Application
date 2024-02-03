package com.assignment.aspd.commentservice.exception.bad.request;

import com.assignment.aspd.commentservice.exception.CommentServiceException;

public abstract class CommentServiceBadRequestException extends CommentServiceException {
    public CommentServiceBadRequestException(Throwable cause) {
        super(cause);
    }

    public CommentServiceBadRequestException(String message) {
        super(message);
    }
}
