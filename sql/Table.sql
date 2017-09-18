DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS members;

create table customers
(
  id serial primary key,
  userid text not null unique,
  password text not null,
  name text not null,
  email text not null unique
);
create table items
(
  id serial primary key,
  name text not null,
  price int not null,
  img text not null,
  comment text not null
);
create table members
(
  id serial primary key,
  name text not null,
  birthday TIMESTAMP not null,
  phase int not null,
  plase text not null,
  img text not null
);