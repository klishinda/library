package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
    int id;
    String name;
    int pages;

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }
}
