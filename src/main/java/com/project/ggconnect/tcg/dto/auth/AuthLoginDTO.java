package com.project.ggconnect.tcg.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthLoginDTO {

    @NotBlank(message = "Email is required!")
    @Email(message = "Email invalid. You must use a valid email! (ex: user@email.com).")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;
}
