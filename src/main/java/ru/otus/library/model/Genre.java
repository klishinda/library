package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Genre {
    int id;
    String name;

    public Genre(String name) {
        this.name = name;
    }
}
