package com.assignment.aspd.reactionservice.mapper;

import com.assignment.aspd.reactionservice.beans.request.ReactionRequest;
import com.assignment.aspd.reactionservice.beans.response.ReactionResponse;
import com.assignment.aspd.reactionservice.model.Reaction;
import com.assignment.aspd.reactionservice.model.ReactionType;
import com.assignment.aspd.reactionservice.model.TargetType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReactionMapper {
    public ReactionResponse map(Reaction reaction){
        ReactionResponse reactionResponse=new ReactionResponse();
        Optional.ofNullable(reaction).map(Reaction::getId).ifPresent(reactionResponse::setId);
        Optional.ofNullable(reaction).map(Reaction::getReactionType).map(ReactionType::toString).ifPresent(reactionResponse::setReactionType);
        Optional.ofNullable(reaction).map(Reaction::getTargetType).map(TargetType::toString).ifPresent(reactionResponse::setTargetType);
        Optional.ofNullable(reaction).map(Reaction::getTargetId).ifPresent(reactionResponse::setTargetId);
        return reactionResponse;
    }

    public Reaction map(ReactionRequest reactionRequest){
        Reaction reaction=new Reaction();
        Optional.ofNullable(reactionRequest.getReactionType()).map(ReactionType::valueOf).ifPresent(reaction::setReactionType);
        Optional.ofNullable(reactionRequest.getTargetType()).map(TargetType::valueOf).ifPresent(reaction::setTargetType);
        return reaction;
    }
}
