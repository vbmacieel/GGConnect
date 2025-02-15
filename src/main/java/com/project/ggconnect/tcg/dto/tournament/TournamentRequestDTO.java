package com.project.ggconnect.tcg.dto.tournament;

import com.project.ggconnect.tcg.model.User;
import com.project.ggconnect.tcg.model.enums.TournamentFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TournamentRequestDTO {

    @NotBlank(message = "Tournament name is required!")
    @Size(max = 100, message = "Tournament name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Tournament format is required!")
    private TournamentFormat format;

    @NotBlank(message = "You need to inform the organizer!")
    private User organizer;
    
    public TournamentRequestDTO(String name, TournamentFormat format, User organizer) {
        this.name = name;
        this.format = format;
        this.organizer = organizer;
    }
}
