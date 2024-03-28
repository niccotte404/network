create table posts (
    id uuid primary key not null,
    user_id uuid not null,
    title varchar(255),
    description varchar(255),
    image_url varchar(255)
);

alter table posts owner to "user";
alter table posts add foreign key (user_id) references users(id);