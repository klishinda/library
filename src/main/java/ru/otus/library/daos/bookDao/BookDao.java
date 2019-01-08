package ru.otus.library.daos.bookDao;

import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.Book;
import ru.otus.library.model.BookInfo;

import java.util.List;

public interface BookDao {
    void addBook(Book book);
    void addAuthorForBook(int id_author, int id_book);
    void addGenreForBook(int id_genre, int id_book);
    void deleteBook(int id);
    List<AuthorBooksCounting> getBooksCountByAuthors();
    List<BookInfo> getAllBooks();
}