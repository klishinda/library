package ru.otus.library.daos.genreDao;

import ru.otus.library.model.Genre;

import java.util.List;

public interface GenreDao {
    void addGenre(Genre genre);
    void deleteGenre(int id);
    Genre getGenreById(int id);
    List<Genre> getAllGenres();
}
