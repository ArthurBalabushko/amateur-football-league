package com.itacademy.database.dao;

import com.itacademy.database.entity.Invitation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvitationDao implements BaseDao<Long, Invitation> {

    private static final InvitationDao INSTANCE = new InvitationDao();

    public static InvitationDao getInstance() {
        return INSTANCE;
    }
}
