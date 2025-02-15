package com.project.ggconnect.tcg.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.ggconnect.tcg.model.Tournament;
import com.project.ggconnect.tcg.service.TournamentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService service;

    @PostMapping("/create")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament, UriComponentsBuilder uriBuilder) {
        Tournament newTournament = service.createTournament(tournament);
        URI location = uriBuilder.path("tournaments/{id}")
                .buildAndExpand(newTournament.getId())
                .toUri();
        return ResponseEntity.created(location).body(newTournament);
    }

    @GetMapping
    public ResponseEntity<List<Tournament>> findAllTournaments() {
        List<Tournament> tournaments = service.findAllTournaments();
        return ResponseEntity.ok(tournaments);
    }
}
