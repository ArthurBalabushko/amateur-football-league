package com.itacademy.database.dao;

import com.itacademy.database.entity.Sponsor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SponsorDao implements BaseDao<Long, Sponsor> {

    private static final SponsorDao INSTANCE = new SponsorDao();

    public static SponsorDao getInstance() {
        return INSTANCE;
    }
}
