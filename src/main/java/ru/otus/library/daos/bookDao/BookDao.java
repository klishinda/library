package ru.otus.library.daos.bookDao;

import ru.otus.library.model.*;

import java.util.List;

public interface BookDao {
    void addBook(BookList bookList);
    void deleteBook(int id);
    List<Book> getBooksByTitle(String name);
    List<AuthorBooksCounting> getBooksCountByAuthors();
    List<BookList> getAllBooks();
}