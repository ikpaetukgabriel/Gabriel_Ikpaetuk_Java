create schema if not exists book_store;
use book_store;

create table if not exists book (
  book_id int not null auto_increment primary key,
    isbn varchar (15) not null,
    publish_date date not null,
    author_id int not null,
    title varchar (70) not null,
    publisher_id int not null,
    price decimal(5,2) not null
);

create table if not exists author (
  author_id int not null auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state char(2) not null,
    postal_code varchar(25) not null,
    phone varchar(15) not null,
    email varchar(60) not null
);

create table if not exists publisher (
  publisher_id int not null auto_increment primary key,
    name varchar(50)not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state char(2) not null,
    postal_code varchar(25) not null,
    phone varchar(15) not null,
    email varchar(60) not null
);

/* Foreign Keys: book */
alter table book add constraint fk_book_author foreign key (author_id) references author(author_id);
alter table book add constraint fk_book_publisher foreign key (publisher_id) references publisher(publisher_id);


/* Test Database */
create schema if not exists book_store_test;
use book_store_test;

create table if not exists book (
  book_id int not null auto_increment primary key,
    isbn varchar (15) not null,
    publish_date date not null,
    author_id int not null,
    title varchar (70) not null,
    publisher_id int not null,
    price decimal(5,2) not null
);

create table if not exists author (
  author_id int not null auto_increment primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state char(2) not null,
    postal_code varchar(25) not null,
    phone varchar(15) not null,
    email varchar(60) not null
);

create table if not exists publisher (
  publisher_id int not null auto_increment primary key,
    name varchar(50)not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state char(2) not null,
    postal_code varchar(25) not null,
    phone varchar(15) not null,
    email varchar(60) not null
);

/* Foreign Keys: book */
alter table book add constraint fk_book_author foreign key (author_id) references author(author_id);
alter table book add constraint fk_book_publisher foreign key (publisher_id) references publisher(publisher_id);
