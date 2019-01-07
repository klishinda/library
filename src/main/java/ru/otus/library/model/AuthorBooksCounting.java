package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorBooksCounting {
    private String surname;
    private String name;
    private int counting;
}
