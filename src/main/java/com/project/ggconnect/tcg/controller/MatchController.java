package com.project.ggconnect.tcg.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.project.ggconnect.tcg.dto.match.MatchRequestDTO;
import com.project.ggconnect.tcg.dto.match.MatchResponseDTO;
import com.project.ggconnect.tcg.service.MatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService service;

    @GetMapping("/tournaments/{tournamentId}")
    public ResponseEntity<List<MatchResponseDTO>> findAllByTornament(@PathVariable Long tournamentId) {
        List<MatchResponseDTO> matchesResponse = service.findAllMatchesByTournament(tournamentId);
        return ResponseEntity.ok(matchesResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<MatchResponseDTO> findById(@PathVariable Long id) {
        MatchResponseDTO matchResponse = service.findById(id);
        return ResponseEntity.ok(matchResponse);
    }

    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(@RequestBody MatchRequestDTO dto, UriComponentsBuilder uriBuilder) {
        MatchResponseDTO matchResponse = service.createMatch(dto);
        URI location = uriBuilder.path("matches/{id}")
                .buildAndExpand(matchResponse.getId())
                .toUri();
        
        return ResponseEntity.created(location).body(matchResponse);
    }

    @PostMapping("/winner/{id}")
    public ResponseEntity<MatchResponseDTO> registerWinner(@PathVariable Long id, @RequestBody Long winnerId) {
        MatchResponseDTO matchResponse = service.registerWinnerMatch(id, winnerId);
        return ResponseEntity.ok(matchResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        service.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
