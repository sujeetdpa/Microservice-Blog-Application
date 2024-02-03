package com.assignment.aspd.blogservice.service.impl;

import com.assignment.aspd.blogservice.apis.comment.CommentApi;
import com.assignment.aspd.blogservice.apis.reaction.ReactionApi;
import com.assignment.aspd.blogservice.beans.request.BlogRequest;
import com.assignment.aspd.blogservice.beans.response.BlogResponse;
import com.assignment.aspd.blogservice.beans.response.BlogResponseList;
import com.assignment.aspd.blogservice.exception.not.found.BlogNotFoundException;
import com.assignment.aspd.blogservice.mapper.BlogMapper;
import com.assignment.aspd.blogservice.model.Blog;
import com.assignment.aspd.blogservice.repository.BlogRepository;
import com.assignment.aspd.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;

    private BlogMapper blogMapper;

    private CommentApi commentApi;

    private ReactionApi reactionApi;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, BlogMapper blogMapper, CommentApi commentApi, ReactionApi reactionApi) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
        this.commentApi = commentApi;
        this.reactionApi = reactionApi;
    }

    @Override
    public BlogResponseList getBlogs() {
        //Fetching all blogs
        List<Blog> blogs = blogRepository.findAll();
        List<BlogResponse> blogResponses = blogs.stream().map(blogMapper::map).collect(Collectors.toList());
        BlogResponseList responseList=new BlogResponseList();
        responseList.setNumOfItems(blogResponses.size());
        responseList.setBlogResponses(blogResponses);
        return responseList;
    }

    @Override
    public BlogResponse getBlog(Long id) {
        //Fetching the blog with given id
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + id));
        BlogResponse blogResponse = blogMapper.map(blog);
        return blogResponse;
    }

    @Override
    public BlogResponse newBlog(BlogRequest blogRequest) {
        //Creating new Blog
        Blog blog = blogMapper.map(blogRequest);
        Blog savedBlog = blogRepository.save(blog);
        BlogResponse blogResponse = blogMapper.map(savedBlog);
        return blogResponse;
    }

    @Override
    public BlogResponse updateBlog(Long id, BlogRequest blogRequest) {
        //Updating title and content of the blog with given id
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + id));
        Optional.ofNullable(blogRequest.getTitle()).ifPresent(blog::setTitle);
        Optional.ofNullable(blogRequest.getContent()).ifPresent(blog::setContent);
        Blog updatedBlog = blogRepository.save(blog);
        BlogResponse blogResponse = blogMapper.map(updatedBlog);
        return blogResponse;
    }

    @Override
    public void deleteBlog(Long id) {
        //Deleting the Blog with given id
        blogRepository.deleteById(id);

        //Deleting the comments associated with that blog

        commentApi.deleteCommentByBlog(id);

        //Deleting the reactions associated with that blog
        reactionApi.deleteByTargetTypeAndTargetId(id,"BLOG");
    }

    @Override
    public BlogResponseList searchBlog(String key) {
        List<Blog> blogs = Optional.ofNullable(key).map(blogRepository::searchByTitle).orElseThrow(() -> new BlogNotFoundException("No Blog found matching string: " + key));
        List<BlogResponse> blogResponses = blogs.stream().map(blogMapper::map).collect(Collectors.toList());
        BlogResponseList responseList=new BlogResponseList();
        responseList.setNumOfItems(blogResponses.size());
        responseList.setBlogResponses(blogResponses);
        return responseList;
    }
}
