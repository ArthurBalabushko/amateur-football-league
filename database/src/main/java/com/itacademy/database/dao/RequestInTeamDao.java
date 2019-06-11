package com.itacademy.database.dao;

import com.itacademy.database.entity.RequestInTeam;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestInTeamDao implements BaseDao<Long, RequestInTeam> {

    private static final RequestInTeamDao INSTANCE = new RequestInTeamDao();

    public static RequestInTeamDao getInstance() {
        return INSTANCE;
    }
}
