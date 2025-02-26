package com.project.ggconnect.tcg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ggconnect.tcg.dto.match.MatchRequestDTO;
import com.project.ggconnect.tcg.dto.match.MatchResponseDTO;
import com.project.ggconnect.tcg.exception.InvalidArgumentException;
import com.project.ggconnect.tcg.model.Match;
import com.project.ggconnect.tcg.model.Tournament;
import com.project.ggconnect.tcg.model.User;
import com.project.ggconnect.tcg.repository.MatchRepository;
import com.project.ggconnect.tcg.util.MatchMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final TournamentService tournamentService;
    private final UserService userService;

    public List<MatchResponseDTO> findAllMatchesByTournament(Long tournamentId) {
        Tournament tournament = tournamentService.findTournamentEntityById(tournamentId);
        
        List<Match> matchesList = matchRepository.findByTournament(tournament);
        return matchesList.stream().map(MatchMapper::toDto).toList();
    }

    public MatchResponseDTO findById(Long id) {
        Match match = matchRepository.findById(id).orElseThrow(() -> new InvalidArgumentException("Match not found"));

        return MatchMapper.toDto(match);
    }

    public MatchResponseDTO createMatch(MatchRequestDTO dto) {
        Tournament tournament = tournamentService.findTournamentEntityById(dto.getTournament());
        User player1 = userService.findUserById(dto.getPlayer1());
        User player2 = userService.findUserById(dto.getPlayer2());

        Match newMatch = new Match(tournament, player1, player2);
        matchRepository.save(newMatch);

        return MatchMapper.toDto(newMatch);
    }

    public MatchResponseDTO registerWinnerMatch(Long matchId, Long winnerId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new InvalidArgumentException("Match not found"));
        match.setResult(String.valueOf(winnerId));

        matchRepository.save(match);
        return MatchMapper.toDto(match);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
