package com.itacademy.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schedule", schema = "football_league_storage")
public class Schedule implements BaseEntity<Schedule.ScheduleId> {

    @EmbeddedId
    private ScheduleId id;

    @OneToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Embeddable
    public static class ScheduleId implements Serializable {

        @ManyToOne
        @JoinColumn(name = "football_field_id")
        private FootballField footballField;

        @Column(name = "day")
        private LocalDate date;

        @Column(name = "time_start")
        private LocalTime timeStart;

        @Column(name = "time_finish")
        private LocalTime timeFinish;
    }
}
