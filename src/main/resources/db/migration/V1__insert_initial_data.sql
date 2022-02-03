insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'COMEDY');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'THRILLER');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'FANTASY');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'ACTION');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'ROMANCE');
insert into categories (id, name) values (nextval('CATEGORIES_SEQ'), 'WESTERN');

insert into person_roles (id, name) values (nextval('PERSON_ROLES_SEQ'), 'ACTOR');
insert into person_roles (id, name) values (nextval('PERSON_ROLES_SEQ'), 'DIRECTOR');

insert into user_roles (id, name) values (nextval('USER_ROLES_SEQ'), 'ROLE_ADMIN');
insert into user_roles (id, name) values (nextval('USER_ROLES_SEQ'), 'ROLE_USER');