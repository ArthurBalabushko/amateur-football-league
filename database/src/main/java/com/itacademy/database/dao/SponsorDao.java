package com.itacademy.database.dao;

import com.itacademy.database.entity.Sponsor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SponsorDao {

    private static final SponsorDao INSTANCE = new SponsorDao();

    @SneakyThrows
    public Sponsor save(Session session, Sponsor sponsor) {
        session.save(sponsor);

        return sponsor;
    }

    @SneakyThrows
    public Optional<Sponsor> findById(Session session, Long id) {
        Sponsor sponsor = session.get(Sponsor.class, id);

        return ofNullable(sponsor);
    }

    public static SponsorDao getInstance() {
        return INSTANCE;
    }
}
