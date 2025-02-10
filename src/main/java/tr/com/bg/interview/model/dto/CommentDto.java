package tr.com.bg.interview.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDto {
    @NotBlank(message = "Comment cannot be empty")
    private String comment;
}
