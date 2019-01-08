package ru.otus.library.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.library.model.BookInfo;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BookInfoMapper implements RowMapper<BookInfo> {
    @Override
    public BookInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        String bookTitle = resultSet.getString("title");
        String genre = resultSet.getString("genre");
        String name = resultSet.getString("name");
        int pages = resultSet.getInt("pages");
        return new BookInfo(bookTitle, genre, name, pages);
    }
}