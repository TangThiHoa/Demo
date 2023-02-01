package com.example.task.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {
    private Long id;
    @NotNull(message = "Name not is null")
    private String name;
    @NotNull(message = "Description not is null")
    private String description;
}
