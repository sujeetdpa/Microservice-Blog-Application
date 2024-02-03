package com.assignment.aspd.commentservice.apis.blog.impl;

import com.assignment.aspd.commentservice.apis.blog.BlogApi;
import com.assignment.aspd.commentservice.beans.response.BlogResponse;
import com.assignment.aspd.commentservice.config.ExternalServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class BlogApiImpl implements BlogApi {
    ExternalServiceConfig externalServiceConfig;

    @Autowired
    public BlogApiImpl(ExternalServiceConfig externalServiceConfig) {
        this.externalServiceConfig = externalServiceConfig;
    }

    @Override
    public Optional<BlogResponse> getBlog(Long blogId){
        //Rest api call to blog service to fetch a blog with given id
        RestTemplate restTemplate=new RestTemplate();
        try{
            ResponseEntity<BlogResponse> responseEntity = restTemplate.getForEntity(externalServiceConfig.getBlogService().getHostname() + ":" + externalServiceConfig.getBlogService().getPort() + "/api/blogs/{id}", BlogResponse.class,blogId);
            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.hasBody()){
                return Optional.ofNullable(responseEntity.getBody());
            }
            else {
                return Optional.empty();
            }
        }catch (RestClientException e){
            log.error("Unable to fetch blog with id:"+blogId);
        }
        return Optional.empty();
    }
}
