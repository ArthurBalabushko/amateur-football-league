package com.itacademy.database.repository;

import com.itacademy.database.entity.Manager;
import com.itacademy.database.entity.Team;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;


public class TeamDaoTest extends BaseCase {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Test
    public void save() {

        Manager manager = managerRepository.findByEmail("pp@mail.ru").orElse(null);

        Team team = Team.builder()
                .name("test")
                .manager(manager)
                .build();

        teamRepository.save(team);

        assertNotNull(team.getId());
    }

    @Test
    public void findById() {
        Manager manager = managerRepository.findByEmail("ff@mail.ru").orElse(null);

        Team team = Team.builder()
                .name("test2")
                .manager(manager)
                .build();

        teamRepository.save(team);

        Team resultTeam = teamRepository.findById(team.getId()).orElse(null);

        assertNotNull("Entity is null", resultTeam);
    }

    @Test
    public void findByName() {
        Team team = teamRepository.findByName("FC Arsenal").orElse(null);

        assertNotNull("Entity is null", team);
    }
}
