package com.project.ggconnect.tcg.util;

import com.project.ggconnect.tcg.dto.tournament.TournamentRequestDTO;
import com.project.ggconnect.tcg.dto.tournament.TournamentResponseDTO;
import com.project.ggconnect.tcg.model.Tournament;
import com.project.ggconnect.tcg.model.User;

public final class TournamentMapper {

    private TournamentMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated!");
    }

    public static Tournament toEntity(TournamentRequestDTO dto, User organizer) {
        return new Tournament(dto.getName(), dto.getFormat(), organizer);
    }

    public static TournamentResponseDTO toDto(Tournament tournament, User organizer) {
        return new TournamentResponseDTO(tournament, organizer);
    }
}
