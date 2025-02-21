package com.project.ggconnect.tcg.dto.tournament;

import java.util.List;

import com.project.ggconnect.tcg.dto.user.UserResponseDTO;
import com.project.ggconnect.tcg.model.Match;
import com.project.ggconnect.tcg.model.Tournament;
import com.project.ggconnect.tcg.model.User;

import lombok.Data;

@Data
public class TournamentResponseDTO {

    private Long id;
    private String name;
    private String format;
    private String status;
    private UserResponseDTO organizer;
    private List<Match> matches;

    public TournamentResponseDTO(Tournament tournament, User user) {
        this.id = tournament.getId();
        this.name = tournament.getName();
        this.format = tournament.getFormat().name();
        this.status = tournament.getStatus().name();
        this.matches = tournament.getMatches();
        this.organizer = new UserResponseDTO(user);
    }
}
