package com.assignment.aspd.blogservice.apis.reaction.impl;

import com.assignment.aspd.blogservice.apis.reaction.ReactionApi;
import com.assignment.aspd.blogservice.config.ExternalServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ReactionApiImpl implements ReactionApi {
    private ExternalServiceConfig externalServiceConfig;

    public ReactionApiImpl(ExternalServiceConfig externalServiceConfig) {
        this.externalServiceConfig = externalServiceConfig;
    }

    @Override
    public void deleteByTargetTypeAndTargetId(Long targetId, String targetType) {
        //Making api call to reaction service to delete reactions.
        RestTemplate restTemplate=new RestTemplate();
        try{
            restTemplate.delete(externalServiceConfig.getReactionService().getHostname()+":"+externalServiceConfig.getReactionService().getPort()+"/api/reactions/delete/target?targetId={targetId}&targetType={targetType}",targetId,targetType);
        }catch (RestClientException e){
            log.error("Unable to delete reaction with id: "+targetId+" and type: "+targetType);
        }
    }
}
