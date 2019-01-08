package ru.otus.library.daos.genreDao;

import ru.otus.library.model.Genre;

public interface GenreDao {
    void addGenre(Genre genre);
    void deleteGenre(int id);
}
