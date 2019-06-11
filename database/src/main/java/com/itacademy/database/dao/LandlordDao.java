package com.itacademy.database.dao;

import com.itacademy.database.entity.Landlord;
import com.itacademy.database.util.SessionManager;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;

import java.util.Optional;

import static com.itacademy.database.entity.QLandlord.landlord;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LandlordDao implements BaseDao<Long, Landlord> {

    private static final LandlordDao INSTANCE = new LandlordDao();

    public Optional<Landlord> findByEmail(String email) {
        @Cleanup Landlord resultLandlord = new JPAQuery<Landlord>(SessionManager.getSession())
                .select(landlord)
                .from(landlord)
                .where(landlord.email.eq(email))
                .fetchOne();

        return Optional.ofNullable(resultLandlord);
    }

    public static LandlordDao getInstance() {
        return INSTANCE;
    }
}
