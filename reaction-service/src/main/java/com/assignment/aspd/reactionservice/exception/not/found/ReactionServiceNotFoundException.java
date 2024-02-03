package com.assignment.aspd.reactionservice.exception.not.found;

import com.assignment.aspd.reactionservice.exception.ReactionServiceException;

public abstract class ReactionServiceNotFoundException extends ReactionServiceException {
    public ReactionServiceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReactionServiceNotFoundException(String message) {
        super(message);
    }
}
