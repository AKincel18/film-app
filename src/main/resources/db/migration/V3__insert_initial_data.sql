insert into categories (id, name) values (nextval('DICTIONARIES_SEQ'), 'Comedy');
insert into categories (id, name) values (nextval('DICTIONARIES_SEQ'), 'Thriller');
insert into categories (id, name) values (nextval('DICTIONARIES_SEQ'), 'Fantasy');
insert into categories (id, name) values (nextval('DICTIONARIES_SEQ'), 'Action');
insert into categories (id, name) values (nextval('DICTIONARIES_SEQ'), 'Romance');
insert into categories (id, name) values (nextval('DICTIONARIES_SEQ'), 'Western');

insert into person_roles (id, name) values (nextval('DICTIONARIES_SEQ'), 'Actor');
insert into person_roles (id, name) values (nextval('DICTIONARIES_SEQ'), 'Director');

insert into user_roles (id, name) values (nextval('DICTIONARIES_SEQ'), 'ROLE_ADMIN');
insert into user_roles (id, name) values (nextval('DICTIONARIES_SEQ'), 'ROLE_USER');
insert into user_roles (id, name) values (nextval('DICTIONARIES_SEQ'), 'ROLE_MODERATOR');