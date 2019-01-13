package ru.otus.library.daos.genreDao;

import ru.otus.library.model.Genre;

import java.util.List;

public interface GenreDao {
    void addGenre(Genre genre);
    void deleteGenre(Long id);
    Genre getGenreById(Long id);
    List<Genre> getAllGenres();
}
