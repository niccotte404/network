create table address (
    id bigint primary key not null,
    country varchar(255),
    city varchar(255)
);

alter table address owner to "user";