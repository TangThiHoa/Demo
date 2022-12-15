package com.example.task.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {
    @NotNull(message = "Email not is null")
    private String email;
    @NotNull(message = "Password is not null")
    private String password;
}
