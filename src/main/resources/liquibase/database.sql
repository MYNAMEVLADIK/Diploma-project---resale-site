CREATE TABLE users
(
    id         SERIAL primary key,
    username   TEXT,
    email      TEXT,
    password   TEXT,
    first_name TEXT,
    last_name  TEXT,
    phone      TEXT,
    image      TEXT,
    user_role  VARCHAR
);


CREATE TABLE ads
(
    id          SERIAL primary key,
    user_id     int REFERENCES users (id),
    price       int,
    title       text,
    image_path  text,
    description text
);


CREATE TABLE comments
(
    id        SERIAL primary key,
    user_id   int,
    ads_id    int,
    text      text,
    create_at timestamp
);