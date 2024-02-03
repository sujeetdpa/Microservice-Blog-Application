package com.assignment.aspd.reactionservice.exception.not.found;

public class ReactionNotFoundException extends ReactionServiceNotFoundException {
    public ReactionNotFoundException(Throwable cause) {
        super(cause);
    }

    public ReactionNotFoundException(String message) {
        super(message);
    }
}
