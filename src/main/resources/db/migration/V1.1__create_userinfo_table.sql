create table user_info (
    user_id uuid primary key not null,
    email varchar(255) unique,
    description varchar(255),
    image_url varchar(255)
);

alter table user_info owner to "user";
alter table user_info add foreign key (user_id) references users(id);