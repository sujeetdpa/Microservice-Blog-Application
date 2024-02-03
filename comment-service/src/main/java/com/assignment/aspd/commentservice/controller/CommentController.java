package com.assignment.aspd.commentservice.controller;

import com.assignment.aspd.commentservice.beans.request.CommentRequest;
import com.assignment.aspd.commentservice.beans.response.CommentResponse;
import com.assignment.aspd.commentservice.beans.response.CommentResponseList;
import com.assignment.aspd.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/comments",produces = {MediaType.APPLICATION_JSON_VALUE})
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/blog/{id}")
    public ResponseEntity<CommentResponseList> getBlogComments(@PathVariable(value = "id") Long blogId){
        CommentResponseList responseList=commentService.getBlogComments(blogId);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> newComment(@Validated @RequestBody CommentRequest commentRequest){
        CommentResponse commentResponse=commentService.newComment(commentRequest);
        return new ResponseEntity<>(commentResponse,HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long id){
        CommentResponse commentResponse=commentService.getComment(id);
        return new ResponseEntity<>(commentResponse,HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id,@RequestBody CommentRequest commentRequest){
        CommentResponse commentResponse=commentService.updateComment(id,commentRequest);
        return new ResponseEntity<>(commentResponse,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/blog/{id}/comments")
    public ResponseEntity<?> deleteCommentsOfBlog(@PathVariable(value = "id") Long blogId){
        commentService.deleteCommentsOfBlog(blogId);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }



}
