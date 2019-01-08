DROP TABLE IF EXISTS book_authors;
DROP TABLE IF EXISTS genres_of_books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS books;


create table authors (
                       id serial,
                       surname varchar(250) not null,
                       name varchar(250)
);
alter table authors add constraint authors_pkey PRIMARY KEY (id);
alter table authors add constraint authors_u1 UNIQUE (name, surname);

create table books (
                     id serial,
                     name varchar(250) not null,
                     pages integer
);
alter table books add constraint books_pkey PRIMARY KEY (id);
alter table books add constraint books_u1 UNIQUE (name);

create table genres (
                      id serial,
                      name varchar(100) not null
);
alter table genres add constraint genres_pkey PRIMARY KEY (id);
alter table genres add constraint genres_u1 UNIQUE (name);

create table book_authors (
                            id_book integer not null,
                            id_author integer
);
alter table book_authors add constraint book_authors_pkey PRIMARY KEY (id_book, id_author);
alter table book_authors add constraint book_authors_fkey1 FOREIGN KEY (id_book) REFERENCES books(id);
alter table book_authors add constraint book_authors_fkey2 FOREIGN KEY (id_author) REFERENCES authors(id);

create table genres_of_books (
                               id_book integer not null,
                               id_genre integer
);
alter table genres_of_books add constraint genres_of_books_pkey PRIMARY KEY (id_book, id_genre);
alter table genres_of_books add constraint genres_of_books_fkey1 FOREIGN KEY (id_book) REFERENCES books(id);
alter table genres_of_books add constraint genres_of_books_fkey2 FOREIGN KEY (id_genre) REFERENCES genres(id);



ALTER SEQUENCE authors_id_seq RESTART WITH 20;
ALTER SEQUENCE books_id_seq RESTART WITH 20;
ALTER SEQUENCE genres_id_seq RESTART WITH 20;