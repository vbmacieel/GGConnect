package com.project.ggconnect.tcg.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.ggconnect.tcg.dto.tournament.TournamentRequestDTO;
import com.project.ggconnect.tcg.dto.tournament.TournamentResponseDTO;
import com.project.ggconnect.tcg.model.Tournament;
import com.project.ggconnect.tcg.service.TournamentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService service;

    @PostMapping("/create")
    public ResponseEntity<Tournament> createTournament(@Valid @RequestBody TournamentRequestDTO dto, UriComponentsBuilder uriBuilder) {
        Tournament newTournament = service.createTournament(dto);
        URI location = uriBuilder.path("tournaments/{id}")
                .buildAndExpand(newTournament.getId())
                .toUri();
        return ResponseEntity.created(location).body(newTournament);
    }

    @GetMapping
    public ResponseEntity<List<TournamentResponseDTO>> findAllTournaments() {
        List<TournamentResponseDTO> tournamentsResponse = service.findAllTournaments();
        return ResponseEntity.ok(tournamentsResponse);
    }

    @GetMapping("/organizer/{organizerId}")
    public ResponseEntity<List<TournamentResponseDTO>> findTournamentsByOrganizer(@PathVariable Long organizerId) {
        List<TournamentResponseDTO> tournamentsResponse = service.findTournamentsByOrganizer(organizerId);
        return ResponseEntity.ok(tournamentsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentResponseDTO> findTournamentById(@PathVariable Long id) {
        TournamentResponseDTO tournamentResponse = service.findTournamentById(id);
        return ResponseEntity.ok(tournamentResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TournamentResponseDTO> updateTournament(@PathVariable Long id, @Valid @RequestBody TournamentRequestDTO dto) {
        TournamentResponseDTO tournamentResponse = service.updateTournament(id, dto);
        return ResponseEntity.ok(tournamentResponse);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        service.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }
}
