package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    int id;
    String name;
    int pages;
    List<Author> authors;
    List<Genre> genres;

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }
}
