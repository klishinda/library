package ru.otus.library.daos.bookDao;

import ru.otus.library.model.*;

import java.util.List;

public interface BookDao {
    void addBook(Book book);
    void deleteBook(Long id);
    List<Book> getBooksByTitle(String name);
    List<AuthorBooksCounting> getBooksCountByAuthors();
    List<Book> getAllBooks();
}