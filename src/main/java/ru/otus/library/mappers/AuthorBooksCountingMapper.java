package ru.otus.library.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.library.model.AuthorBooksCounting;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorBooksCountingMapper implements RowMapper<AuthorBooksCounting>{
    @Override
    public AuthorBooksCounting mapRow(ResultSet resultSet, int i) throws SQLException {
        String surname = resultSet.getString("surname");
        String name = resultSet.getString("name");
        int counting = resultSet.getInt("cnt");
        return new AuthorBooksCounting(surname, name, counting);
    }
}
