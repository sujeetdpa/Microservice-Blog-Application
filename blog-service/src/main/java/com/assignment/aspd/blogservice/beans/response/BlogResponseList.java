package com.assignment.aspd.blogservice.beans.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponseList {
    private int numOfItems;
    private List<BlogResponse> blogResponses;
}
