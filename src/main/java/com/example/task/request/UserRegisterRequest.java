package com.example.task.request;
import com.example.task.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {
    private Long id;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private Set<Role> roles;

}
