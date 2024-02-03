package com.assignment.aspd.reactionservice.service;

import com.assignment.aspd.reactionservice.beans.request.ReactionRequest;
import com.assignment.aspd.reactionservice.beans.response.ReactionResponse;

public interface ReactionService {
    ReactionResponse addReaction(ReactionRequest reactionRequest);

    void deleteReaction(Long id);

    void deleteReactionByTargetIdAndTargetType(Long targetId, String targetType);

}
