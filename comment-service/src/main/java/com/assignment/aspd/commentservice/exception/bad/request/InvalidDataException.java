package com.assignment.aspd.commentservice.exception.bad.request;

public class InvalidDataException extends CommentServiceBadRequestException{
    public InvalidDataException(Throwable cause) {
        super(cause);
    }

    public InvalidDataException(String message) {
        super(message);
    }
}
