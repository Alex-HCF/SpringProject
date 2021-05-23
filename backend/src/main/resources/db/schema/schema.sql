set statement_timeout to '10s';

CREATE TABLE IF NOT EXISTS category
(
    id        serial primary key,
    parent_id int references category (id),
    name      varchar not null
);



CREATE TABLE IF NOT EXISTS person
(
    id       serial primary key,
    login    varchar unique not null,
    password varchar        not null,
    name     varchar        not null,
    surname  varchar,
    role     varchar(30)    not null,
    status   varchar(30)    not null
);



CREATE TABLE IF NOT EXISTS location
(
    id                 serial primary key,

    region_fias_id     uuid,
    area_fias_id       uuid,
    city_fias_id       uuid,
    settlement_fias_id uuid,
    street_fias_id     uuid,
    house_fias_id      uuid,

    longitude          float,
    latitude           float

--     note                  int references note
);


CREATE TABLE IF NOT EXISTS note
(
    id          serial primary key,
    owner_id    int references person,
    headline    varchar,
    price       int,
    describe    varchar,
    category_id int references category,
    location_id int references location unique,
    create_date        timestamp,
    status          varchar,
    close_date  timestamp
);

CREATE TABLE IF NOT EXISTS message
(
    id               serial primary key,
    sender_id int references person not null,
    recipient_id int references person not null,
    note_id          int references note,
    text          varchar,
    date             timestamp

);

-- CREATE TABLE IF NOT EXISTS note_status
--  (
--      id serial primary key ,
--      value varchar
-- );
