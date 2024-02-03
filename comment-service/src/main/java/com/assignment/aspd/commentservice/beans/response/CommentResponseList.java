package com.assignment.aspd.commentservice.beans.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseList {
    private Integer numOfItems;
    private List<CommentResponse> commentResponses;
}
