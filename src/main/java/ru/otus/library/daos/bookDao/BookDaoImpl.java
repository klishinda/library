package ru.otus.library.daos.bookDao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.library.mappers.AuthorBooksCountingMapper;
import ru.otus.library.mappers.BookInfoMapper;
import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.Book;
import ru.otus.library.model.BookInfo;

import java.util.HashMap;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcTemplate jdbc;

    public BookDaoImpl(NamedParameterJdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;
    }

    @Override
    public void addBook(Book book) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("name", book.getName());
        params.put("pages", book.getPages());
        jdbc.update("insert into books (name, pages) values (:name, :pages)", params);
    }

    @Override
    public void addAuthorForBook(int id_author, int id_book) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("id_author", id_author);
        params.put("id_book", id_book);
        jdbc.update("insert into book_authors values (:id_book, :id_author)", params);
    }

    @Override
    public void addGenreForBook(int id_genre, int id_book) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("id_genre", id_genre);
        params.put("id_book", id_book);
        jdbc.update("insert into genres_of_books values (:id_book, :id_genre)", params);
    }

    @Override
    public void deleteBook(int id) {
        final HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbc.update("delete from genres_of_books gb where gb.id_book = :id;" +
                "delete from book_authors ba where ba.id_book = :id;" +
                "delete from books b where b.id = :id", params);
    }

    @Override
    public List<AuthorBooksCounting> getBooksCountByAuthors() {
        return jdbc.query("select a.surname, a.name, coalesce((select count(*) from book_authors b where b.id_author = a.id group by b.id_author), 0) cnt from authors a order by a.surname;", new AuthorBooksCountingMapper());
    }

    @Override
    public List<BookInfo> getAllBooks() {
        return jdbc.query("select b.name title, string_agg(distinct g.name,', ') genre, string_agg(distinct a.name || ' ' || a.surname, ', ') as name, b.pages from books b " +
                "left join genres_of_books gb on gb.id_book = b.id " +
                "left join genres g on g.id = gb.id_genre " +
                "left join book_authors ba on ba.id_book = b.id " +
                "left join authors a on a.id = ba.id_author " +
                "group by b.name, b.pages order by b.name;", new BookInfoMapper());
    }
}