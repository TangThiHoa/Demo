package com.example.task.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEdit {
    private Long id;
    private String username;
    private String email;
    private String passwordOld;
    private String password;
    private String confirmPassword;
}
