insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'Comedy');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'Thriller');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'Fantasy');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'Action');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'Romance');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'Western');

insert into person_roles (id, role) values (nextval('PERSON_ROLES_SEQ'), 'Actor');
insert into person_roles (id, role) values (nextval('PERSON_ROLES_SEQ'), 'Director');

insert into user_roles (id, role) values (nextval('USER_ROLES_SEQ'), 'ROLE_ADMIN');
insert into user_roles (id, role) values (nextval('USER_ROLES_SEQ'), 'ROLE_USER');