package tr.com.bg.interview.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {
    @NotBlank(message = "Name is required")
    private String name;

    private String description; // optional
}
