package com.assignment.aspd.reactionservice.exception.bad.request;

public class InvalidDataException extends ReactionServiceBadRequestException{
    public InvalidDataException(Throwable cause) {
        super(cause);
    }

    public InvalidDataException(String message) {
        super(message);
    }
}
