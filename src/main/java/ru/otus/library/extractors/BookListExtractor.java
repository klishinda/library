package ru.otus.library.extractors;

import org.springframework.jdbc.core.ResultSetExtractor;
import ru.otus.library.model.Author;
import ru.otus.library.model.Book;
import ru.otus.library.model.BookList;
import ru.otus.library.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class BookListExtractor implements ResultSetExtractor<List<BookList>> {
    @Override
    public List<BookList> extractData(ResultSet rs) throws SQLException {
        List<BookList> booksList = new ArrayList<>();
        Map<Book, Set<Author>> bookAuthors = new HashMap<>();
        Map<Book, Set<Genre>> bookGenres = new HashMap<>();
        while (rs.next()) {
            Book book = new Book(rs.getInt("id"), rs.getString("title"), rs.getInt("pages"));
            Author author = new Author();
            Genre genre = new Genre();
            author.setName(rs.getString("name"));
            author.setSurname(rs.getString("surname"));
            genre.setName(rs.getString("genre"));
            Set<Author> authors = bookAuthors.get(book);
            if (authors == null) {
                Set<Author> newAuthors = new HashSet<>();
                newAuthors.add(author);
                bookAuthors.put(book, newAuthors);
            } else {
                authors.add(author);
            }
            Set<Genre> genres = bookGenres.get(book);
            if (genres == null) {
                Set<Genre> newGenres = new HashSet<>();
                newGenres.add(genre);
                bookGenres.put(book, newGenres);
            } else {
                genres.add(genre);
            }
        }

        Set<Book> allBooks = new HashSet<>();
        for (Map.Entry<Book, Set<Author>> authorMap : bookAuthors.entrySet()) {
            allBooks.addAll(bookAuthors.keySet());
        }
        for (Map.Entry<Book, Set<Genre>> genreMap : bookGenres.entrySet()) {
            allBooks.addAll(bookGenres.keySet());
        }

        for (Book b: allBooks) {
            booksList.add(new BookList(b, bookAuthors.get(b), bookGenres.get(b)));
        }

        return booksList;
    }
}