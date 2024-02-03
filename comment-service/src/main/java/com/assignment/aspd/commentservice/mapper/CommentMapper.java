package com.assignment.aspd.commentservice.mapper;

import com.assignment.aspd.commentservice.beans.request.CommentRequest;
import com.assignment.aspd.commentservice.beans.response.CommentResponse;
import com.assignment.aspd.commentservice.model.Comment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentMapper {
    public CommentResponse map(Comment comment){
        CommentResponse commentResponse=new CommentResponse();
        Optional.ofNullable(comment).map(Comment::getId).ifPresent(commentResponse::setId);
        Optional.ofNullable(comment).map(Comment::getBlogId).ifPresent(commentResponse::setBlogId);
        Optional.ofNullable(comment).map(Comment::getText).ifPresent(commentResponse::setText);
        return commentResponse;
    }

    public Comment map(CommentRequest commentRequest){
        Comment comment=new Comment();
        Optional.ofNullable(commentRequest.getText()).ifPresent(comment::setText);
        return comment;
    }
}
