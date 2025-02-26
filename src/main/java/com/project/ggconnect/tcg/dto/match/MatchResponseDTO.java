package com.project.ggconnect.tcg.dto.match;

import com.project.ggconnect.tcg.dto.user.UserResponseDTO;
import com.project.ggconnect.tcg.model.Match;

import lombok.Data;

@Data
public class MatchResponseDTO {

    private Long id;
    private Long tournament;
    private UserResponseDTO player1;
    private UserResponseDTO player2;
    private String result;

    public MatchResponseDTO(Match match) {
        this.id = match.getId();
        this.tournament = match.getTournament().getId();
        this.player1 = new UserResponseDTO(match.getPlayer1());
        this.player2 = new UserResponseDTO(match.getPlayer2());
        this.result = match.getResult();
    }
}
