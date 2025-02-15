package com.project.ggconnect.tcg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ggconnect.tcg.model.Match;
import com.project.ggconnect.tcg.model.Tournament;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTournament(Tournament tournament);
}
