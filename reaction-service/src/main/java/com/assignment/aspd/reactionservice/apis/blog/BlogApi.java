package com.assignment.aspd.reactionservice.apis.blog;

import com.assignment.aspd.reactionservice.beans.response.BlogResponse;

import java.util.Optional;

public interface BlogApi {
    Optional<BlogResponse> getBlog(Long blogId);
}
