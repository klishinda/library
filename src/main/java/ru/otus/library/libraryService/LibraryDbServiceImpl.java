package ru.otus.library.libraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.library.daos.authorDao.AuthorDao;
import ru.otus.library.daos.bookDao.BookDao;
import ru.otus.library.daos.genreDao.GenreDao;
import ru.otus.library.model.Author;
import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.Book;
import ru.otus.library.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryDbServiceImpl implements LibraryDbService{
    private AuthorDao authorDao;
    private GenreDao genreDao;
    private BookDao bookDao;

    @Autowired
    public LibraryDbServiceImpl(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
    }

    public void addBook(String title, Long pages, List<Long> idAuthors, List<Long> idGenres) {
        Book book = new Book(title, pages);
        book.setAuthors(idAuthors.stream().map(Author::new).collect(Collectors.toSet()));
        book.setGenres(idGenres.stream().map(Genre::new).collect(Collectors.toSet()));
        bookDao.addBook(book);
    }

    @Override
    public List<AuthorBooksCounting> getBooksCountByAuthors() {
        return bookDao.getBooksCountByAuthors();
    }

    @Override
    public List<Book> getBooksByTitle(String name) {
        return bookDao.getBooksByTitle(name);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    public void deleteBook(Long id) {
        bookDao.deleteBook(id);
    }

    @Override
    public List<Author> getAllAuthorsByName(String name) {
        return authorDao.getAuthorByName(name);
    }

    @Override
    public void addAuthor(Author author) {
        authorDao.addAuthor(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorDao.deleteAuthor(id);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorDao.getAuthorById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    @Override
    public void addGenre(Genre genre) {
        genreDao.addGenre(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreDao.deleteGenre(id);
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreDao.getGenreById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAllGenres();
    }
}
