package ru.otus.library.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.library.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String surname = resultSet.getString("surname");
        String name = resultSet.getString("name");
        return new Author(id, surname, name);
    }
}