package com.itacademy.database.dao;

import com.itacademy.database.entity.RequestOnField;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestOnFieldDao implements BaseDao<Long, RequestOnField> {

    private static final RequestOnFieldDao INSTANCE = new RequestOnFieldDao();

    public static RequestOnFieldDao getInstance() {
        return INSTANCE;
    }
}
