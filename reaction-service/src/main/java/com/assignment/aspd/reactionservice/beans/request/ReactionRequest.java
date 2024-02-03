package com.assignment.aspd.reactionservice.beans.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReactionRequest {
    @NotBlank
    private String reactionType;

    @NotBlank
    private String targetType;

    @NotNull
    private Long targetId;
}
