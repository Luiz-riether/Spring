package test.luiz.springboot2.requests;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = "The name cannot be empty or null")
    private String name;
}
