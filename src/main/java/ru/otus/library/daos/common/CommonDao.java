package ru.otus.library.daos.common;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.library.SqlScripts;
import ru.otus.library.mappers.AuthorBooksCountingMapper;
import ru.otus.library.mappers.LibraryMapper;
import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.Library;

import java.util.List;

@Repository
public class CommonDao implements ICommonDao{
    private final NamedParameterJdbcTemplate jdbc;

    public CommonDao(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public List<AuthorBooksCounting> countAuthorsBooks() {
        return jdbc.query(SqlScripts.COUNTING_BOOKS_BY_AUTHORS.getSql(), new AuthorBooksCountingMapper());
    }

    @Override
    public List<Library> getAllLibrary() {
        return jdbc.query(SqlScripts.GET_ALL_LIBRARY.getSql(), new LibraryMapper());
    }
}
