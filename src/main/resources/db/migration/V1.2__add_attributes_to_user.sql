alter table users add column email varchar(255) unique;
alter table users add column description varchar(255);
alter table users add column image_url varchar(255);
alter table users add column address_id bigint;

alter table users add foreign key (address_id) references address(id);