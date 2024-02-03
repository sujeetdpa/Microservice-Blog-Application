package com.assignment.aspd.blogservice.mapper;

import com.assignment.aspd.blogservice.beans.request.BlogRequest;
import com.assignment.aspd.blogservice.beans.response.BlogResponse;
import com.assignment.aspd.blogservice.model.Blog;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BlogMapper {
    public BlogResponse map(Blog blog){
        BlogResponse blogResponse=new BlogResponse();
        Optional.ofNullable(blog).map(Blog::getId).ifPresent(blogResponse::setId);
        Optional.ofNullable(blog).map(Blog::getTitle).ifPresent(blogResponse::setTitle);
        Optional.ofNullable(blog).map(Blog::getContent).ifPresent(blogResponse::setContent);
        return blogResponse;
    }
    public Blog map(BlogRequest blogRequest){
        Blog blog=new Blog();
        Optional.ofNullable(blogRequest).map(BlogRequest::getTitle).ifPresent(blog::setTitle);
        Optional.ofNullable(blogRequest).map(BlogRequest::getContent).ifPresent(blog::setContent);
        return blog;
    }
}
