package com.assignment.aspd.blogservice.service;

import com.assignment.aspd.blogservice.beans.request.BlogRequest;
import com.assignment.aspd.blogservice.beans.response.BlogResponse;
import com.assignment.aspd.blogservice.beans.response.BlogResponseList;

public interface BlogService {
    BlogResponseList getBlogs();

    BlogResponse getBlog(Long id);

    BlogResponse newBlog(BlogRequest blogRequest);

    BlogResponse updateBlog(Long id, BlogRequest blogRequest);

    void deleteBlog(Long id);

    BlogResponseList searchBlog(String key);
}
