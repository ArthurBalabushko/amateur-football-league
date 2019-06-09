package com.itacademy.database.mapper;

public interface BaseMapper<E, D> {

    D mapToDto(E entity);
}
