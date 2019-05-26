package com.itacademy.database.entity;

import lombok.Getter;

@Getter
public enum Status {
    WAITING("Ожидает"),
    OK("Одобрено"),
    REJECTED("Отклонено");

    private String description;

    Status(String description) {
        this.description = description;
    }
}
