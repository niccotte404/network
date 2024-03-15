create table users
(
    id uuid not null primary key,
    password varchar(255),
    username varchar(255)
);

create table roles
(
    id int not null primary key,
    name varchar(255)
);

create table user_roles
(
    user_id uuid,
    role_id int
);

alter table users owner to "user";
alter table roles owner to "user";
alter table user_roles owner to "user";
alter table user_roles ADD FOREIGN KEY (user_id) REFERENCES users(id);
alter table user_roles ADD FOREIGN KEY (role_id) REFERENCES roles(id);