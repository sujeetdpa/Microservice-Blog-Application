package com.assignment.aspd.commentservice.beans.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    @NonNull
    private Long blogId;

    @NotBlank
    private String text;
}
