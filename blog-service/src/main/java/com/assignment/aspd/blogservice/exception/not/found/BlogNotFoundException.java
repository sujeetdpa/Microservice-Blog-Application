package com.assignment.aspd.blogservice.exception.not.found;

public class BlogNotFoundException extends BlogServiceNotFoundException{
    public BlogNotFoundException(Throwable cause) {
        super(cause);
    }

    public BlogNotFoundException(String message) {
        super(message);
    }
}
