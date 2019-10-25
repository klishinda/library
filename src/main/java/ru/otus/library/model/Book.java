package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String name;
    private Long pages;
    private Set<Author> authors;
    private Set<Genre> genres;

    public Book(String name, Long pages) {
        this.name = name;
        this.pages = pages;
    }

    public Book(long id, String name, long pages) {
        this.id = id;
        this.name = name;
        this.pages = pages;
    }
}
