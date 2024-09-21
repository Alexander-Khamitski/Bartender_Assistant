package com.teachmeskills.bartender_assistant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDTO {
    @NotBlank(message = "Login is required")
    @Size(min = 1, max = 128, message = "Login must be between 1 and 128 characters")
    private String login;

    @NotBlank(message = "Password is required")
    @Size(min = 1, message = "Password must have at least 1 character")
    private String password;
}
