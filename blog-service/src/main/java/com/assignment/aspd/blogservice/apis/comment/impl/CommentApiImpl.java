package com.assignment.aspd.blogservice.apis.comment.impl;

import com.assignment.aspd.blogservice.apis.comment.CommentApi;
import com.assignment.aspd.blogservice.beans.response.CommentResponse;
import com.assignment.aspd.blogservice.config.ExternalServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class CommentApiImpl implements CommentApi {
    ExternalServiceConfig externalServiceConfig;

    public CommentApiImpl(ExternalServiceConfig externalServiceConfig) {
        this.externalServiceConfig = externalServiceConfig;
    }

    @Override
    public Optional<CommentResponse> getComment(Long commentId) {
        //Making api call to comment service to fetch a comment with given id
        RestTemplate restTemplate=new RestTemplate();
        try{
            ResponseEntity<CommentResponse> responseEntity = restTemplate.getForEntity(externalServiceConfig.getCommentService().getHostname() + ":" + externalServiceConfig.getCommentService().getPort() + "/api/comments/{id}", CommentResponse.class,commentId);
            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()){
                return Optional.ofNullable(responseEntity.getBody());
            }
            else {
                return Optional.empty();
            }
        }catch (RestClientException e){
            log.error("Unable to fetch comment with id:"+commentId);
        }
        return Optional.empty();
    }

    @Override
    public void deleteCommentByBlog(Long blogId) {
        //Making api call to comment service to delete the comments
        RestTemplate restTemplate=new RestTemplate();
        try{
            restTemplate.delete(externalServiceConfig.getCommentService().getHostname() + ":" + externalServiceConfig.getCommentService().getPort() + "/api/comments/blog/{blogId}/comments",blogId);
        }catch (RestClientException e){
            log.error("Unable to delete comments with blog Id:"+blogId);
        }
    }
}
