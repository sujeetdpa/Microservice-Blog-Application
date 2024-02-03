package com.assignment.aspd.reactionservice.apis.comment;

import com.assignment.aspd.reactionservice.beans.response.BlogResponse;
import com.assignment.aspd.reactionservice.beans.response.CommentResponse;

import java.util.Optional;

public interface CommentApi {
    Optional<CommentResponse> getComment(Long commentId);
}
