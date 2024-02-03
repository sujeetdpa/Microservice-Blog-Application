package com.assignment.aspd.blogservice.exception.not.found;


import com.assignment.aspd.blogservice.exception.BlogServiceException;

public abstract class BlogServiceNotFoundException extends BlogServiceException {
    public BlogServiceNotFoundException(Throwable cause) {
        super(cause);
    }

    public BlogServiceNotFoundException(String message) {
        super(message);
    }
}
