package ru.otus.library.libraryService;

import ru.otus.library.model.Author;
import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.Book;
import ru.otus.library.model.Genre;

import java.util.List;

public interface LibraryDbService {
    void addBook(String title, Long pages, List<Long> idAuthors, List<Long> idGenres);
    List<AuthorBooksCounting> getBooksCountByAuthors();
    List<Book> getBooksByTitle(String name);
    List<Book> getAllBooks();
    void deleteBook(Long id);
    List<Author> getAllAuthorsByName(String name);
    void addAuthor(Author author);
    void deleteAuthor(Long id);
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
    void addGenre(Genre genre);
    void deleteGenre(Long id);
    Genre getGenreById(Long id);
    List<Genre> getAllGenres();
}
