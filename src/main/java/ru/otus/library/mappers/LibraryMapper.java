package ru.otus.library.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.library.model.Library;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LibraryMapper implements RowMapper<Library> {
    @Override
    public Library mapRow(ResultSet resultSet, int i) throws SQLException {
        String bookTitle = resultSet.getString("title");
        String genre = resultSet.getString("genre");
        String name = resultSet.getString("name");
        int pages = resultSet.getInt("pages");
        return new Library(bookTitle, genre, name, pages);
    }
}