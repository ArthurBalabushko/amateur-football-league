package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@ToString(exclude = "team")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "manager", schema = "football_league_storage")
@PrimaryKeyJoinColumn(name = "user_id")
public class Manager extends User {

    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL)
    private Team team;

    @Builder
    public Manager(String firstName, String lastName, LocalDate birthDay, String phoneNumber, String email, String password, Role role) {
        super(firstName, lastName, birthDay, phoneNumber, email, password, role);
    }
}
