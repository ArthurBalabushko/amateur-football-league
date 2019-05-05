CREATE DATABASE football_league_repository;

CREATE TABLE role
(
    id   SERIAL       NOT NULL PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id           BIGSERIAL    NOT NULL PRIMARY KEY,
    first_name   VARCHAR(128) NOT NULL,
    last_name    VARCHAR(128) NOT NULL,
    birth_day    DATE,
    phone_number VARCHAR(32),
    email        VARCHAR(128) NOT NULL UNIQUE,
    password     VARCHAR(128) NOT NULL,
    role_id      INTEGER      NOT NULL REFERENCES role(id)
);