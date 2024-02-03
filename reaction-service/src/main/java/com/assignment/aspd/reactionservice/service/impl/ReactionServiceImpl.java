package com.assignment.aspd.reactionservice.service.impl;

import com.assignment.aspd.reactionservice.apis.blog.BlogApi;
import com.assignment.aspd.reactionservice.apis.comment.CommentApi;
import com.assignment.aspd.reactionservice.beans.request.ReactionRequest;
import com.assignment.aspd.reactionservice.beans.response.BlogResponse;
import com.assignment.aspd.reactionservice.beans.response.CommentResponse;
import com.assignment.aspd.reactionservice.beans.response.ReactionResponse;
import com.assignment.aspd.reactionservice.exception.bad.request.InvalidDataException;
import com.assignment.aspd.reactionservice.mapper.ReactionMapper;
import com.assignment.aspd.reactionservice.model.Reaction;
import com.assignment.aspd.reactionservice.model.TargetType;
import com.assignment.aspd.reactionservice.repository.ReactionRepository;
import com.assignment.aspd.reactionservice.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionServiceImpl implements ReactionService {
    private ReactionRepository reactionRepository;
    private ReactionMapper reactionMapper;

    private BlogApi blogApi;

    private CommentApi commentApi;

    @Autowired
    public ReactionServiceImpl(ReactionRepository reactionRepository, ReactionMapper reactionMapper, BlogApi blogApi, CommentApi commentApi) {
        this.reactionRepository = reactionRepository;
        this.reactionMapper = reactionMapper;
        this.blogApi = blogApi;
        this.commentApi = commentApi;
    }

    @Override
    public ReactionResponse addReaction(ReactionRequest reactionRequest) {
        //Adding a reaction for BLOG or COMMENT
        Reaction reaction = reactionMapper.map(reactionRequest);
        if (reaction.getTargetType().equals(TargetType.BLOG)){
            //Contacting blog service to check if the blog exists for the given targetId
            BlogResponse blogResponse = blogApi.getBlog(reactionRequest.getTargetId()).orElseThrow(() -> new InvalidDataException("Invalid blog id: "+reactionRequest.getTargetId()));
            reaction.setTargetId(blogResponse.getId());
        }
        else if(reaction.getTargetType().equals(TargetType.COMMENT)){
            //Contacting comment service to check if the comment exists for the given targetId
            CommentResponse commentResponse = commentApi.getComment(reactionRequest.getTargetId()).orElseThrow(() -> new InvalidDataException("Invalid comment id: " + reactionRequest.getTargetId()));
            reaction.setTargetId(commentResponse.getId());
        }else {
            //Throwing error if the targetId provided by the client does not belong to either blog or comment
            throw new IllegalArgumentException("Invalid Input");
        }
        Reaction saveReaction = reactionRepository.save(reaction);
        ReactionResponse reactionResponse = reactionMapper.map(saveReaction);
        return reactionResponse;
    }

    @Override
    public void deleteReaction(Long id) {
        //Deleting the reaction with given reaction id
        reactionRepository.deleteById(id);
    }

    @Override
    public void deleteReactionByTargetIdAndTargetType(Long targetId, String targetType) {
        //Deleting the reactions using targetType and target id
        TargetType targetType1 = Optional.ofNullable(targetType).map(TargetType::valueOf).orElseThrow(() -> new InvalidDataException("Invalid target type"));
        List<Reaction> reactions = reactionRepository.findByTargetIdAndTargetType(targetId, targetType1);
        reactionRepository.deleteAll(reactions);
    }
}
