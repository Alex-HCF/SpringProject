set statement_timeout to '10s';

CREATE TABLE IF NOT EXISTS category
(
    id        serial primary key,
    parent_id int references category (id),
    name      varchar not null
);

CREATE TABLE IF NOT EXISTS location
(
    id      serial primary key,
    country varchar,
    region  varchar,
    town    varchar
);

CREATE TABLE IF NOT EXISTS person
(
    id       serial primary key,
    login    varchar unique not null,
    password varchar        not null,
    name     varchar        not null,
    surname  varchar,
    role     varchar(30) not null,
    status   varchar(30) not null
);

CREATE TABLE IF NOT EXISTS note
(
    id       serial primary key,
    owner    int references person,
    headline varchar,
    price    int,
    describe varchar,
    category int references category,
    location int references location
);