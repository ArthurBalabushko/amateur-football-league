package com.itacademy.database.repository;

import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Position;
import com.itacademy.database.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long>, QuerydslPredicateExecutor<Player> {

    Optional<Player> findByEmail(String email);

    List<Player> findAllByPosition(Position position);

    List<Player> findAllByTeam(Team team);

    Page<Player> findAllByPositionAndAndBirthDayBetween(Position position, LocalDate ageFrom,
                                                            LocalDate ageTo, Pageable pageable);

    Page<Player> findAllByPositionAndTeamNullAndBirthDayBetween(Position position, LocalDate ageFrom,
                                                                   LocalDate ageTo, Pageable pageable);

    @Modifying
    @Query("update Player p set p.team = :team where id = :id")
    void update(@Param("team") Team team, @Param("id") Long id);
}
