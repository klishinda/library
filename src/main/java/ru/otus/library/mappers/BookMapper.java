package ru.otus.library.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.library.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int pages = resultSet.getInt("pages");
        return new Book(id, name, pages);
    }
}