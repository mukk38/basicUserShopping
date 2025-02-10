package com.basic.user.shopping.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDto {
    @NotBlank(message = "Comment cannot be empty")
    private String comment;
}
