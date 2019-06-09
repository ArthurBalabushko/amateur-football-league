package com.itacademy.database.dao;

import com.itacademy.database.entity.FootballField;
import com.itacademy.database.util.SessionManager;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static com.itacademy.database.entity.QFootballField.footballField;
import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FootballFieldDao implements BaseDao<Long, FootballField> {

    private static final FootballFieldDao INSTANCE = new FootballFieldDao();

    public Optional<FootballField> findByName(String name) {
        FootballField resultField = new JPAQuery<FootballField>(SessionManager.getSession())
                .select(footballField)
                .from(footballField)
                .where(footballField.name.eq(name))
                .fetchOne();

        return ofNullable(resultField);
    }

    public static FootballFieldDao getInstance() {
        return INSTANCE;
    }
}
