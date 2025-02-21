package com.project.ggconnect.tcg.dto.user;

import com.project.ggconnect.tcg.model.User;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    } 
}
