package ru.otus.library.daos.genreDao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.library.model.Genre;

import java.util.HashMap;

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
    public void deleteGenre(int id) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("update genres_of_books gb set gb.id_genre = null where gb.id_genre = :id;" +
                "delete from genres where id = :id", params);
    }
}