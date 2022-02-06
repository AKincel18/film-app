create table if not exists categories (
    id          int primary key not null,
    name        varchar(100) unique not null
);

create table if not exists person_roles (
    id          int primary key not null,
    name        varchar(100) unique not null
);

create table if not exists user_roles (
    id          int primary key not null,
    name        varchar(100) unique not null
);

create table if not exists persons
(
    id             int primary key not null,
    birth_date     date,
    first_name     varchar(255) not null,
    last_name      varchar(255) not null,
    person_role_id int,
    foreign key(person_role_id) references person_roles(id)
);

create table if not exists films
(
    id           int primary key not null,
    budget       double precision,
    name         varchar(255),
    release_date date,
    running_time integer,
    category_id  int,
    director_id  int,
    foreign key(category_id) references categories(id),
    foreign key(director_id) references persons(id)
);

create table if not exists films_persons
(
    film_id         int not null,
    person_id       int not null,
    primary key (film_id, person_id),
    foreign key(film_id) references films(id),
    foreign key(person_id) references persons(id)
);

create table if not exists users
(
    id              int primary key not null,
    email           varchar(30) unique,
    password        varchar(255) not null,
    username        varchar(20) unique,
    user_role_id    int,
    foreign key(user_role_id) references user_roles(id)
);