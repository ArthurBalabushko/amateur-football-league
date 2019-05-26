CREATE DATABASE football_league_repository;

CREATE SCHEMA football_league_storage;

SET SEARCH_PATH TO football_league_storage;

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
    role_id      INTEGER      NOT NULL REFERENCES role (id)
);

CREATE TABLE manager
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    user_id     BIGINT    NOT NULL UNIQUE REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE landlord
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    user_id     BIGINT    NOT NULL UNIQUE REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE sponsor
(
    id   BIGSERIAL    NOT NULL PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE team
(
    id         BIGSERIAL    NOT NULL PRIMARY KEY,
    name       VARCHAR(128) NOT NULL UNIQUE,
    manager_id BIGINT       NOT NULL UNIQUE REFERENCES manager (id) ON DELETE CASCADE
);

CREATE TABLE team_sponsor
(
    team_id    BIGINT NOT NULL REFERENCES team (id) ON DELETE CASCADE,
    sponsor_id BIGINT NOT NULL REFERENCES sponsor (id) ON DELETE CASCADE,
    PRIMARY KEY (team_id, sponsor_id)
);

CREATE TABLE position
(
    id   SERIAL       NOT NULL PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE player
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    user_id     BIGINT    NOT NULL UNIQUE REFERENCES users (id) ON DELETE CASCADE,
    position_id INTEGER   NOT NULL REFERENCES position (id),
    growth      INTEGER,
    weight      INTEGER,
    team_id     BIGINT    REFERENCES team (id) ON DELETE SET NULL
);

CREATE TABLE football_field
(
    id              BIGSERIAL    NOT NULL PRIMARY KEY,
    name            VARCHAR(128) NOT NULL UNIQUE,
    location_city   VARCHAR(128) NOT NULL,
    location_street VARCHAR(128) NOT NULL,
    phone_number    VARCHAR(32),
    landlord_id     BIGINT       NOT NULL REFERENCES landlord (id) ON DELETE CASCADE
);

CREATE TABLE invitation_in_team
(
    id        BIGSERIAL   NOT NULL PRIMARY KEY,
    date      DATE        NOT NULL,
    player_id BIGINT      NOT NULL REFERENCES player (id) ON DELETE CASCADE,
    team_id   BIGINT      NOT NULL REFERENCES team (id) ON DELETE CASCADE,
    status    VARCHAR(32) NOT NULL
);

CREATE TABLE request_in_team
(
    id        BIGSERIAL   NOT NULL PRIMARY KEY,
    date      DATE        NOT NULL,
    player_id BIGINT      NOT NULL REFERENCES player (id) ON DELETE CASCADE,
    team_id   BIGINT      NOT NULL REFERENCES team (id) ON DELETE CASCADE,
    status    VARCHAR(32) NOT NULL
);

CREATE TABLE request_on_football_field
(
    id                BIGSERIAL   NOT NULL PRIMARY KEY,
    date_request      DATE        NOT NULL,
    day               DATE        NOT NULL,
    time_start        TIME        NOT NULL,
    time_finish       TIME        NOT NULL,
    football_field_id BIGINT      NOT NULL REFERENCES football_field (id) ON DELETE CASCADE,
    team_id           BIGINT      NOT NULL REFERENCES team (id) ON DELETE CASCADE,
    status            VARCHAR(32) NOT NULL
);

CREATE TABLE schedule
(
    football_field_id BIGINT NOT NULL REFERENCES football_field (id) ON DELETE CASCADE,
    day               DATE   NOT NULL,
    time_start        TIME   NOT NULL,
    time_finish       TIME   NOT NULL,
    team_id           BIGINT DEFAULT NULL REFERENCES team (id) ON DELETE SET DEFAULT,
    PRIMARY KEY (football_field_id, day, time_start, time_finish)
);