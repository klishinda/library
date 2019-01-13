package ru.otus.library.daos.genreDao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.library.mappers.GenreMapper;
import ru.otus.library.model.Genre;

import java.util.HashMap;
import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcTemplate jdbc;

    public GenreDaoImpl(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public void addGenre(Genre genre) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("name", genre.getName());
        jdbc.update("insert into genres (name) values (:name)", params);
    }

    @Override
    public void deleteGenre(Long id) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("update genres_of_books gb set gb.id_genre = null where gb.id_genre = :id;" +
                "delete from genres where id = :id", params);
    }

    @Override
    public Genre getGenreById(Long id) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.queryForObject("select * from genres g where g.id = :id", params, new GenreMapper());
    }

    @Override
    public List<Genre> getAllGenres() {
        return jdbc.query("select * from genres g", new GenreMapper());
    }
}