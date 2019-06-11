package com.itacademy.database.util;

import com.itacademy.database.entity.FootballField;
import com.itacademy.database.entity.Landlord;
import com.itacademy.database.entity.Location;
import com.itacademy.database.entity.Manager;
import com.itacademy.database.entity.Player;
import com.itacademy.database.entity.Position;
import com.itacademy.database.entity.Role;
import com.itacademy.database.entity.Sponsor;
import com.itacademy.database.entity.Team;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataHelper {

    private static TestDataHelper INSTANCE = new TestDataHelper();
    private static SessionFactory sessionFactory = SessionManager.getFactory();

    public void deleteTestData() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete from Schedule ").executeUpdate();
        session.createQuery("delete from RequestOnField ").executeUpdate();
        session.createQuery("delete from FootballField ").executeUpdate();
        session.createQuery("delete from Landlord ").executeUpdate();
        session.createQuery("delete from Sponsor ").executeUpdate();
        session.createQuery("delete from Invitation ").executeUpdate();
        session.createQuery("delete from RequestInTeam ").executeUpdate();
        session.createQuery("delete from Player ").executeUpdate();
        session.createQuery("delete from Team ").executeUpdate();
        session.createQuery("delete from Manager ").executeUpdate();
        session.createQuery("delete from Position ").executeUpdate();
        session.createQuery("delete from Role ").executeUpdate();
        session.getTransaction().commit();
    }

    public void importTestData() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Role rolePlayer = saveRole(session, "player");
        Role roleManager = saveRole(session, "manager");
        Role roleLandlord = saveRole(session, "landlord");

        Manager manager1 = saveManager(session, "Петя", "Петров",
                LocalDate.of(1992, 7, 10), "+375 33 378 12 34",
                "pp@mail.ru", "1234", roleManager);
        Manager manager2 = saveManager(session, "Федор", "Федоров",
                LocalDate.of(1991, 10, 17), "+375 33 378 12 35",
                "ff@mail.ru", "1234", roleManager);
        Landlord landlord1 = saveLandlord(session, "Константин", "Костин",
                LocalDate.of(1988, 10, 17), "+375 33 378 33 35",
                "kk@mail.ru", "1234", roleLandlord);

        Sponsor etihadAirways = saveSponsor(session, "Etihad Airways");
        Sponsor emirates = saveSponsor(session, "Emirates");
        Sponsor jeep = saveSponsor(session, "Jeep");
        Sponsor xBet = saveSponsor(session, "1xBET");

        Location location = Location.builder()
                .city("Минск")
                .street("Серова")
                .build();

        FootballField footballField = saveFootballField(session, "СК Динамо", location,
                "+375 17 327 36 11", landlord1);

        Team fcArsenal = saveTeam(session, "FC Arsenal", manager1);
        Team fcBate = saveTeam(session, "FC Bate", manager2);

        Position goalkeeper = savePosition(session, "goalkeeper");
        Position defender = savePosition(session, "defender");
        Position midfielder = savePosition(session, "midfielder");

        Player player1 = savePlayer(session, "Сергей", "Сергеев",
                LocalDate.of(1989, 12, 31), "+375 33 378 14 56",
                "ss@mail.ru", "1234", rolePlayer, goalkeeper, 180, 87, fcArsenal);
        Player player2 = savePlayer(session, "Антон", "Антонов",
                LocalDate.of(1978, 12, 20), "+375 33 378 88 56",
                "aa@mail.ru", "1234", rolePlayer, defender, 190, 90, fcArsenal);
        Player player3 = savePlayer(session, "Андрей", "Андреев",
                LocalDate.of(1989, 5, 31), "+375 33 448 14 56",
                "andr@mail.ru", "1234", rolePlayer, goalkeeper, 180, 87, fcArsenal);
        Player player4 = savePlayer(session, "Павел", "Павлов",
                LocalDate.of(1988, 9, 24), "+375 33 498 10 66",
                "pavel@mail.ru", "1234", rolePlayer, defender, 189, 87, null);
        Player player5 = savePlayer(session, "Ян", "Янковский",
                LocalDate.of(1993, 12, 24), "+375 33 498 19 66",
                "yan@mail.ru", "1234", rolePlayer, midfielder, 199, 97, fcBate);

        session.getTransaction().commit();
    }

    private Landlord saveLandlord
            (Session session, String firstName, String lastName, LocalDate birthDay, String phoneNumber, String email,
             String password, Role role) {

        Landlord landlord = new Landlord(firstName, lastName, birthDay, phoneNumber, email, password, role);

        session.save(landlord);

        return landlord;
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

    private FootballField saveFootballField(Session session, String name, Location location, String phoneNumber,
                                            Landlord landlord) {
        FootballField footballField = FootballField.builder()
                .name(name)
                .location(location)
                .phoneNumber(phoneNumber)
                .landlord(landlord)
                .build();

        session.save(footballField);

        return footballField;
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
             String password, Role role, Position position, Integer growth, Integer weight, Team team) {
        Player player = new Player
                (firstName, lastName, birthDay, phoneNumber, email, password, role, position, growth, weight, team);

        session.save(player);

        return player;
    }

    public static TestDataHelper getInstance() {
        return INSTANCE;
    }
}
