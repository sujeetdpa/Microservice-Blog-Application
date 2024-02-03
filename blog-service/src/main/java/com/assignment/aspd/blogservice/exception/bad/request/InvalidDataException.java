package com.assignment.aspd.blogservice.exception.bad.request;

public class InvalidDataException extends BlogServiceBadRequestException{
    public InvalidDataException(Throwable cause) {
        super(cause);
    }

    public InvalidDataException(String message) {
        super(message);
    }
}
