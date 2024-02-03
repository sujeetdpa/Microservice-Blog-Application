package com.assignment.aspd.reactionservice.beans.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReactionResponse {
    private Long id;
    private String reactionType;
    private String targetType;

    private Long targetId;
}
