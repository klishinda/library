package ru.otus.library;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SqlScripts {
    COUNTING_BOOKS_BY_AUTHORS("select a.surname, a.name, coalesce((select count(*) from book_authors b where b.id_author = a.id group by b.id_author), 0) cnt from authors a order by a.surname;"),
    GET_ALL_LIBRARY("select b.name title, string_agg(distinct g.name,', ') genre, string_agg(distinct a.name || ' ' || a.surname, ', ') as name, b.pages from books b\n" +
                    "left join genres_of_books gb on gb.id_book = b.id\n" +
                    "join genres g on g.id = gb.id_genre\n" +
                    "left join book_authors ba on ba.id_book = b.id\n" +
                    "left join authors a on a.id = ba.id_author\n" +
                    "group by b.name, b.pages;"),
    GET_AUTHOR_BY_NAME("select * from authors a where upper(a.surname) like '%:name%'"),
    ADD_AUTHOR("insert into authors (surname, name) values (:surname, :name)"),
    DELETE_AUTHOR("update book_authors b set b.id_author = null where b.id_author = :id;" +
                  "delete from authors where id = :id"),
    ;

    private String sql;
}
