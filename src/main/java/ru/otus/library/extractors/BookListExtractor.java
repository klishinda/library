package ru.otus.library.extractors;

import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.library.model.Author;
import ru.otus.library.model.Book;
import ru.otus.library.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class BookListExtractor implements ResultSetExtractor<List<Book>> {
    @Override
    public List<Book> extractData(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        Map<Long, Set<Author>> bookAuthors = new HashMap<>();
        Map<Long, Set<Genre>> bookGenres = new HashMap<>();
        Set<Long> idBooks = new HashSet<>();
        Long idBook;
        while (rs.next()) {
            Author author = new Author();
            Genre genre = new Genre();
            idBook = rs.getLong("id");
            author.setName(rs.getString("name"));
            author.setSurname(rs.getString("surname"));
            genre.setName(rs.getString("genre"));

            if (!idBooks.contains(idBook)) {
                idBooks.add(rs.getLong("id"));
                books.add(new Book(idBook, rs.getString("title"), rs.getLong("pages")));
            }
            Set<Author> authors = bookAuthors.get(idBook);
            if (authors == null) {
                Set<Author> newAuthors = new HashSet<>();
                newAuthors.add(author);
                bookAuthors.put(idBook, newAuthors);
            } else {
                authors.add(author);
            }
            Set<Genre> genres = bookGenres.get(idBook);
            if (genres == null) {
                Set<Genre> newGenres = new HashSet<>();
                newGenres.add(genre);
                bookGenres.put(idBook, newGenres);
            } else {
                genres.add(genre);
            }
        }
        for (Book b : books) {
            b.setAuthors(bookAuthors.get(b.getId()));
            b.setGenres(bookGenres.get(b.getId()));
        }

        return books;
    }
}