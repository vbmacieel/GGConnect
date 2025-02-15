package com.project.ggconnect.tcg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ggconnect.tcg.model.Tournament;
import com.project.ggconnect.tcg.model.User;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    List<Tournament> findByOrganizer(User organizer);
}
