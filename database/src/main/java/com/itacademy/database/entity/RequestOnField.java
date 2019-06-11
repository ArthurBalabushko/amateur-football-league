package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "request_on_football_field", schema = "football_league_storage")
public class RequestOnField implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_request", nullable = false)
    private LocalDate date;

    @Column(name = "day", nullable = false)
    private LocalDate day;

    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @Column(name = "time_finish", nullable = false)
    private LocalTime timeFinish;

    @OneToOne
    @JoinColumn(name = "football_field_id", nullable = false)
    private FootballField footballField;

    @OneToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Enumerated(EnumType.STRING)
    private Status status;
}
