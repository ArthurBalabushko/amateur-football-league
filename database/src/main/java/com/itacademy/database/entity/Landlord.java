package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "landlord", schema = "football_league_storage")
@PrimaryKeyJoinColumn(name = "user_id")
public class Landlord extends User {

    @OneToMany(mappedBy = "landlord")
    private Set<FootballField> footballFields = new HashSet<>();

    public Landlord(String firstName, String lastName, LocalDate birthDay, String phoneNumber, String email,
                    String password, Role role, Position position, Integer growth, Integer weight, Team teamsPlayer) {
        super(firstName, lastName, birthDay, phoneNumber, email, password, role);
    }
}
