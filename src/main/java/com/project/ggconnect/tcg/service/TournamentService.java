package com.project.ggconnect.tcg.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.project.ggconnect.tcg.dto.tournament.TournamentRequestDTO;
import com.project.ggconnect.tcg.dto.tournament.TournamentResponseDTO;
import com.project.ggconnect.tcg.exception.InvalidArgumentException;
import com.project.ggconnect.tcg.model.Tournament;
import com.project.ggconnect.tcg.model.User;
import com.project.ggconnect.tcg.repository.TournamentRepository;
import com.project.ggconnect.tcg.util.TournamentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final UserService userService;

    public List<TournamentResponseDTO> findAllTournaments() {
        return tournamentRepository.findAll().stream()
                .map(tournament -> TournamentMapper.toDto(tournament, tournament.getOrganizer())).toList();
    }

    public TournamentResponseDTO findTournamentById(Long id) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new InvalidArgumentException("No tournaments found with this id!"));

        return TournamentMapper.toDto(tournament, tournament.getOrganizer());
    }

    public List<TournamentResponseDTO> findTournamentsByOrganizer(Long organizerId) {
        userService.checkIfUserExists(organizerId);

        User organizer = userService.findUserById(organizerId);
        return tournamentRepository.findByOrganizer(organizer).stream()
                .map(tournaments -> TournamentMapper.toDto(tournaments, tournaments.getOrganizer())).toList();
    }

    public Tournament createTournament(TournamentRequestDTO dto) {
        Tournament newTournament = TournamentMapper.toEntity(dto, userService.findUserById(dto.getOrganizer()));

        return tournamentRepository.save(newTournament);
    }

    public TournamentResponseDTO updateTournament(Long id, TournamentRequestDTO dto) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new InvalidArgumentException("No tournaments found with this id!"));

        Tournament tournamentUpdated = TournamentMapper.toEntity(dto, tournament.getOrganizer());
        tournamentUpdated.setId(tournament.getId());

        tournamentRepository.save(tournamentUpdated);
        return TournamentMapper.toDto(tournamentUpdated, tournament.getOrganizer());
    }

    public void deleteTournament(Long id) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new InvalidArgumentException("No tournaments found with this id!"));

        tournamentRepository.delete(tournament);
    }
}
