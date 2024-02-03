package com.assignment.aspd.blogservice.controller;

import com.assignment.aspd.blogservice.beans.request.BlogRequest;
import com.assignment.aspd.blogservice.beans.response.BlogResponse;
import com.assignment.aspd.blogservice.beans.response.BlogResponseList;
import com.assignment.aspd.blogservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/blogs",produces = {MediaType.APPLICATION_JSON_VALUE})
//Handles the REST Apis request for blog
public class BlogController {
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BlogResponse> getBlog(@PathVariable Long id){
        BlogResponse blogResponse=blogService.getBlog(id);
        return new ResponseEntity<>(blogResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<BlogResponseList> getBlogs(){
        BlogResponseList responseList=blogService.getBlogs();
        return new ResponseEntity<>(responseList,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<BlogResponse> newBlog(@Validated @RequestBody BlogRequest blogRequest){
        BlogResponse response=blogService.newBlog(blogRequest);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<BlogResponse> updateBlog(@PathVariable Long id,@Validated @RequestBody BlogRequest blogRequest){
        BlogResponse response=blogService.updateBlog(id,blogRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/search")
    public ResponseEntity<BlogResponseList> searchBlog(@RequestParam(value = "key") String key){
        BlogResponseList responseList=blogService.searchBlog(key);
        return new ResponseEntity<>(responseList,HttpStatus.OK);
    }
}
