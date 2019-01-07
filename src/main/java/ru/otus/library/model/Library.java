package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Library {
    private String bookTitle;
    private String genre;
    private String authorName;
    private int numberOfPages;
}