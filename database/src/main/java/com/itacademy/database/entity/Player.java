package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "player", schema = "football_league_storage")
@PrimaryKeyJoinColumn(name = "user_id")
public class Player extends User {

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Column(name = "growth")
    private Integer growth;

    @Column(name = "weight")
    private Integer weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Invitation invitation;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RequestInTeam requestInTeam;

    @Builder
    public Player(String firstName, String lastName, LocalDate birthDay, String phoneNumber, String email,
                  String password, Role role, Position position, Integer growth, Integer weight, Team team) {

        super(firstName, lastName, birthDay, phoneNumber, email, password, role);
        this.position = position;
        this.growth = growth;
        this.weight = weight;
        this.team = team;
    }
}
