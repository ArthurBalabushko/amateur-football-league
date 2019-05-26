package com.itacademy.database.dao.util;

import com.itacademy.database.entity.Manager;
import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Position;
import com.itacademy.database.entity.Role;
import com.itacademy.database.entity.Sponsor;
import com.itacademy.database.entity.Team;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataHelper {

    private static TestDataHelper INSTANCE = new TestDataHelper();

    public static TestDataHelper getInstance() {
        return INSTANCE;
    }

    public void importTestData(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Role player = saveRole(session, "player");
            Role manager = saveRole(session, "manager");
            Role landlord = saveRole(session, "landlord");

            Manager manager1 = saveManager(session, "Петя", "Петров",
                    LocalDate.of(1992, 7, 10), "+375 33 378 12 34",
                    "pp@mail.ru", "1234", manager);
            Manager manager2 = saveManager(session, "Федор", "Федоров",
                    LocalDate.of(1991, 10, 17), "+375 33 378 12 35",
                    "ff@mail.ru", "1234", manager);

            Sponsor etihadAirways = saveSponsor(session, "Etihad Airways");
            Sponsor emirates = saveSponsor(session, "Emirates");
            Sponsor jeep = saveSponsor(session, "Jeep");
            Sponsor xBet = saveSponsor(session, "1xBET");

            Team fcArsenal = saveTeam(session, "FC Arsenal", manager1);
            Team fcBate = saveTeam(session, "FC Bate", manager2);

            Position goalkeeper = savePosition(session, "goalkeeper");
            Position defender = savePosition(session, "defender");

            Player player1 = savePlayer(session, "Сергей", "Сергеев",
                    LocalDate.of(1989, 12, 31), "+375 33 378 14 56",
                    "ss@mail.ru", "1234", player, goalkeeper, 180, 87, fcArsenal);
            Player player2 = savePlayer(session, "Антон", "Антонов",
                    LocalDate.of(1978, 12, 20), "+375 33 378 88 56",
                    "aa@mail.ru", "1234", player, defender, 190, 90, fcArsenal);
            Player player3 = savePlayer(session, "Андрей", "Андреев",
                    LocalDate.of(1989, 5, 31), "+375 33 448 14 56",
                    "andr@mail.ru", "1234", player, goalkeeper, 180, 87, fcArsenal);

            etihadAirways.getTeams().add(fcArsenal);
            etihadAirways.getTeams().add(fcBate);
        }
    }

    private Role saveRole(Session session, String name) {
        Role role = Role.builder().name(name).build();
        session.save(role);

        return role;
    }

    private Manager saveManager
            (Session session, String firstName, String lastName, LocalDate birthDay, String phoneNumber, String email,
             String password, Role role) {

        Manager manager = new Manager(firstName, lastName, birthDay, phoneNumber, email, password, role);

        session.save(manager);

        return manager;
    }

    private Sponsor saveSponsor(Session session, String name) {
        Sponsor sponsor = Sponsor.builder().name(name).build();
        session.save(sponsor);

        return sponsor;
    }

    private Team saveTeam(Session session, String name, Manager manager) {
        Team team = Team.builder()
                .name(name)
                .manager(manager)
                .build();

        session.save(team);

        return team;
    }

    private Position savePosition(Session session, String name) {
        Position position = Position.builder().name(name).build();
        session.save(position);

        return position;
    }

    private Player savePlayer
            (Session session, String firstName, String lastName, LocalDate birthDay, String phoneNumber, String email,
             String password, Role role, Position position, Integer growth, Integer weight, Team teamsPlayer) {

        Player player = new Player
                (firstName, lastName, birthDay, phoneNumber, email, password, role, position, growth, weight, teamsPlayer);

        session.save(player);

        return player;
    }
}
