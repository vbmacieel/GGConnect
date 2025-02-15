package com.project.ggconnect.tcg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.ggconnect.tcg.model.Tournament;
import com.project.ggconnect.tcg.repository.TournamentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TournamentService {
    
    private final TournamentRepository repository;

    public Tournament createTournament(Tournament tournament) {
        return repository.save(tournament);
    }

    public List<Tournament> findAllTournaments() {
        return repository.findAll();
    }
}
