package ru.otus.library.daos.authorDao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.library.SqlScripts;
import ru.otus.library.mappers.AuthorMapper;
import ru.otus.library.model.Author;

import java.util.HashMap;
import java.util.List;

@Repository
public class AuthorDao implements IAuthorDao {
    private final NamedParameterJdbcTemplate jdbc;

    public AuthorDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public List<Author> getAuthorByName(String name) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("name", name.toUpperCase());
        return jdbc.query(SqlScripts.GET_AUTHOR_BY_NAME.getSql(), params, new AuthorMapper());
    }

    @Override
    public void addAuthor(Author author) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("surname", author.getSurname());
        params.put("name", author.getName());
        jdbc.update(SqlScripts.ADD_AUTHOR.getSql(), params);
    }

    @Override
    public void deleteAuthor(int id) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update(SqlScripts.DELETE_AUTHOR.getSql(), params);
    }
}