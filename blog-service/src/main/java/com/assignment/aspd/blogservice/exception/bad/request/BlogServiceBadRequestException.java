package com.assignment.aspd.blogservice.exception.bad.request;

import com.assignment.aspd.blogservice.exception.BlogServiceException;

public abstract class BlogServiceBadRequestException extends BlogServiceException {
    public BlogServiceBadRequestException(Throwable cause) {
        super(cause);
    }

    public BlogServiceBadRequestException(String message) {
        super(message);
    }
}
