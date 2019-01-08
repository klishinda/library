package ru.otus.library.daos.authorDao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.library.mappers.AuthorMapper;
import ru.otus.library.model.Author;

import java.util.HashMap;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcTemplate jdbc;

    public AuthorDaoImpl(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public List<Author> getAuthorByName(String name) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("name", name.toUpperCase());
        return jdbc.query("select * from authors a where upper(a.surname) like '%:name%'", params, new AuthorMapper());
    }

    @Override
    public void addAuthor(Author author) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("surname", author.getSurname());
        params.put("name", author.getName());
        jdbc.update("insert into authors (surname, name) values (:surname, :name)", params);
    }

    @Override
    public void deleteAuthor(int id) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("update book_authors b set b.id_author = null where b.id_author = :id;" +
                "delete from authors where id = :id", params);
    }
}