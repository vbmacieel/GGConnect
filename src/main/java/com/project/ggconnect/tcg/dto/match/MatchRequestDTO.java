package com.project.ggconnect.tcg.dto.match;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchRequestDTO {

    @NotNull(message = "You need to inform the tournament for this match!")
    private Long tournament;

    @NotNull(message = "You need to inform the player 1!")
    private Long player1;

    @NotNull(message = "You need to inform the player 2!")
    private Long player2;
}
