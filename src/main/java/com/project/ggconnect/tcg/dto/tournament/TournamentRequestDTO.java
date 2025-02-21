package com.project.ggconnect.tcg.dto.tournament;

import com.project.ggconnect.tcg.model.enums.TournamentFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TournamentRequestDTO {

    @NotBlank(message = "Tournament name is required!")
    @Size(max = 100, message = "Tournament name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Tournament format is required!")
    private TournamentFormat format;

    @NotNull(message = "You need to inform the organizer!")
    private Long organizer;
    
    public TournamentRequestDTO(String name, TournamentFormat format, Long organizer) {
        this.name = name;
        this.format = format;
        this.organizer = organizer;
    }
}
