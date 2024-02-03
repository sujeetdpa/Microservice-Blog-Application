package com.assignment.aspd.commentservice.service;

import com.assignment.aspd.commentservice.beans.request.CommentRequest;
import com.assignment.aspd.commentservice.beans.response.CommentResponse;
import com.assignment.aspd.commentservice.beans.response.CommentResponseList;

public interface CommentService {
    CommentResponseList getBlogComments(Long blogId);

    CommentResponse newComment(CommentRequest commentRequest);

    CommentResponse getComment(Long id);

    CommentResponse updateComment(Long id, CommentRequest commentRequest);

    void deleteCommentsOfBlog(Long blogId);
}
