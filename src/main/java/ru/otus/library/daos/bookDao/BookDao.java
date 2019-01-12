package ru.otus.library.daos.bookDao;

import ru.otus.library.model.*;

import java.util.List;

public interface BookDao {
    void addBook(Book book, List<Integer> authorsId, List<Integer> genresId);
    void deleteBook(int id);
    List<AuthorBooksCounting> getBooksCountByAuthors();
    List<BookInfo> getAllBooks();
}