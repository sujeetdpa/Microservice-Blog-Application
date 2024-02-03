package com.assignment.aspd.reactionservice.exception.bad.request;

import com.assignment.aspd.reactionservice.exception.ReactionServiceException;

public abstract class ReactionServiceBadRequestException extends ReactionServiceException {
    public ReactionServiceBadRequestException(Throwable cause) {
        super(cause);
    }

    public ReactionServiceBadRequestException(String message) {
        super(message);
    }
}
