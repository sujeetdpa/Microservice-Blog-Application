package com.assignment.aspd.blogservice.beans.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
