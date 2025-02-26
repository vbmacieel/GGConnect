package com.project.ggconnect.tcg.util;

import com.project.ggconnect.tcg.dto.match.MatchResponseDTO;
import com.project.ggconnect.tcg.model.Match;

public final class MatchMapper {
    
    private MatchMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated!");
    }

    public static MatchResponseDTO toDto(Match match) {
        return new MatchResponseDTO(match);
    }
}
