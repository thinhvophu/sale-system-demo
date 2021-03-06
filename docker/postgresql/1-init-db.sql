-- database

\c salesystem
drop database postgres;

-- schema

drop schema public;
create schema salesystem;

-- users

create user db_user with password 'db_user';
grant usage, create on schema salesystem to db_user;

set search_path to salesystem;
alter default privileges grant select, insert, update, delete on tables to db_user;

create table items (
    id bigint generated by default as identity constraint pk_item primary key,
    price decimal(9,2) not null check (price > 0),
    created_time timestamp not null default current_timestamp,
    current_stock bigint not null check (current_stock >= 0)
);

create table orders (
    id bigint generated by default as identity constraint pk_order primary key,
    item_id bigint not null,
    quantity int not null check (quantity > 0),
    created_time timestamp not null default current_timestamp,
    constraint fk_order_item foreign key (item_id) references items (id)
)