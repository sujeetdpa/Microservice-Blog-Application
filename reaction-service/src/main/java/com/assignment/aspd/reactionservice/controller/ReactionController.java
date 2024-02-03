package com.assignment.aspd.reactionservice.controller;

import com.assignment.aspd.reactionservice.beans.request.ReactionRequest;
import com.assignment.aspd.reactionservice.beans.response.ReactionResponse;
import com.assignment.aspd.reactionservice.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/reactions",produces = {MediaType.APPLICATION_JSON_VALUE})
public class ReactionController {
    private ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping("/add")
    public ResponseEntity<ReactionResponse> addReaction(@Validated @RequestBody ReactionRequest reactionRequest){
        ReactionResponse response=reactionService.addReaction(reactionRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteReaction(@PathVariable Long id){
        reactionService.deleteReaction(id);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/delete/target")
    public ResponseEntity<?> deleteReactionByTargetIdAndTargetType(@RequestParam(value = "targetId") Long targetId,@RequestParam(value = "targetType") String targetType){
        reactionService.deleteReactionByTargetIdAndTargetType(targetId,targetType);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }
}
