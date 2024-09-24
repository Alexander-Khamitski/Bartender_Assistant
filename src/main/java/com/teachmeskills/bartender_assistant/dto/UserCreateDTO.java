package com.teachmeskills.bartender_assistant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateDTO {

    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
    private String username;

    @NotBlank(message = "Login is required.")
    @Size(min = 3, max = 50, message = "Login must be between 3 and 50 characters.")
    private String login;

    @NotBlank(message = "Password is required.")
    @Size(min = 3, message = "Password must be at least 3 characters long.")
    private String password;
}
