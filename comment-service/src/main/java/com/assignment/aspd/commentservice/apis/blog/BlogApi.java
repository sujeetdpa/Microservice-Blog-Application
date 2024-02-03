package com.assignment.aspd.commentservice.apis.blog;

import com.assignment.aspd.commentservice.beans.response.BlogResponse;

import java.util.Optional;

public interface BlogApi {
    Optional<BlogResponse> getBlog(Long blogId);
}
