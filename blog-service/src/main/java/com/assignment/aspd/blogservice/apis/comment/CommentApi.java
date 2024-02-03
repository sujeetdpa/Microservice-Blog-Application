package com.assignment.aspd.blogservice.apis.comment;

import com.assignment.aspd.blogservice.beans.response.CommentResponse;

import java.util.Optional;

public interface CommentApi {
    Optional<CommentResponse> getComment(Long commentId);

    void deleteCommentByBlog(Long blogId);
}
