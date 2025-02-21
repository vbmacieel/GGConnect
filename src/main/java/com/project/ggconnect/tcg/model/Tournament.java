package com.project.ggconnect.tcg.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.ggconnect.tcg.model.enums.TournamentFormat;
import com.project.ggconnect.tcg.model.enums.TournamentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tournaments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TournamentFormat format;
    
    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> matches;

    public Tournament(String name, TournamentFormat format, User organizer) {
        this.name = name;
        this.format = format;
        this.status = TournamentStatus.CREATED;
        this.organizer = organizer;
        this.matches = new ArrayList<>();
    }
}
