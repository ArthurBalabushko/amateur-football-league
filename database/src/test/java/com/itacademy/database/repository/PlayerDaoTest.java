package com.itacademy.database.repository;

import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Position;
import com.itacademy.database.entity.Role;
import com.itacademy.database.entity.Team;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PlayerDaoTest extends BaseCase {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void save() {
        Role role = roleRepository.findByName("manager").orElse(null);
        Position position = positionRepository.findByName("defender").orElse(null);

        Player player = new Player("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan@mail.ru", "1234", role, position, 190, 90, null);

        playerRepository.save(player);

        assertNotNull(player.getId());
    }

    @Test
    public void findById() {
        Role role = roleRepository.findByName("manager").orElse(null);
        Position position = positionRepository.findByName("defender").orElse(null);

        Player player = new Player("Ivan", "Ivanov", LocalDate.of(1992, 7, 10),
                "+375 33 378 12 34", "ivan2@mail.ru", "1234", role, position, 190, 90, null);

        playerRepository.save(player);

        Player resultPlayer = playerRepository.findById(player.getId()).orElse(null);

        assertNotNull("Entity is null", resultPlayer);
    }

    @Test
    public void findByEmail() {
        Player resultPlayer = playerRepository.findByEmail("ss@mail.ru").orElse(null);

        assertNotNull("Entity is null", resultPlayer);
    }

    @Test
    public void findAllByPosition() {
        Position position = positionRepository.findByName("goalkeeper").orElse(null);
        List<Player> players = playerRepository.findAllByPosition(position);

        assertThat(players, hasSize(2));
    }

    @Test
    public void findAllByTeam() {
        Team team = teamRepository.findByName("FC Arsenal").orElse(null);
        List<Player> players = playerRepository.findAllByTeam(team);

        assertThat(players, hasSize(3));
    }

    @Test
    public void delete() {
        Player player = playerRepository.findByEmail("yan@mail.ru").orElse(null);

        playerRepository.delete(player);

        Player resultPlayer = playerRepository.findByEmail("yan@mail.ru").orElse(null);

        assertNull(resultPlayer);
    }

    @Test
    public void update() {
        Team team = teamRepository.findByName("FC Bate").orElse(null);
        Player player = playerRepository.findByEmail("pavel@mail.ru").orElse(null);
        player.setTeam(team);

        playerRepository.update(team, player.getId());

        Player resultPlayer = playerRepository.findByEmail("pavel@mail.ru").orElse(null);

        assertEquals(resultPlayer.getTeam(), team);
    }

    @Test
    public void findAll() {

        List<Player> players = new ArrayList<>();
        playerRepository.findAll().iterator().forEachRemaining(players::add);
        List<String> emails = players.stream().map(Player::getEmail).collect(Collectors.toList());

        assertThat(emails, contains("ss@mail.ru", "aa@mail.ru", "andr@mail.ru", "pavel@mail.ru", "yan@mail.ru"));
    }
}
