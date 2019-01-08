package ru.otus.library;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import ru.otus.library.daos.authorDao.AuthorDao;
import ru.otus.library.daos.bookDao.BookDao;
import ru.otus.library.daos.genreDao.GenreDao;
import ru.otus.library.model.Author;
import ru.otus.library.model.Book;
import ru.otus.library.model.Genre;
import ru.otus.library.printer.ResultsPrinter;

@ShellComponent
public class ShellCommands {
    private AuthorDao authorDao;
    private GenreDao genreDao;
    private BookDao bookDao;
    private ResultsPrinter printer;

    public ShellCommands(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao, ResultsPrinter printer) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
        this.printer = printer;
    }

    @ShellMethod("Counting all books in the context of the authors")
    private Table getBooksCountByAuthors() {
        return printer.printResultsBooksCountByAuthors(bookDao.getBooksCountByAuthors());
    }

    @ShellMethod("Get all information about library")
    private Table getAllBooks() {
        return printer.printResultsAllBooks(bookDao.getAllBooks());
    }

    @ShellMethod("Get all authors by their surname")
    private Table getAllAuthorsByName(@ShellOption String partOfSurname) {
        return printer.printAuthors(authorDao.getAuthorByName(partOfSurname));
    }

    @ShellMethod("Add new author")
    private void addAuthor(@ShellOption String surname, @ShellOption String name) {
        authorDao.addAuthor(new Author(surname, name));
    }

    @ShellMethod("Remove author")
    private void deleteAuthor(@ShellOption int id) {
        authorDao.deleteAuthor(id);
    }

    @ShellMethod("Add new genre")
    private void addGenre(@ShellOption String name) {
        genreDao.addGenre(new Genre(name));
    }

    @ShellMethod("Remove genre")
    private void deleteGenre(@ShellOption int id) {
        genreDao.deleteGenre(id);
    }

    @ShellMethod("Add new book")
    private void addBook(@ShellOption String name, @ShellOption int pages) {
        bookDao.addBook(new Book(name, pages));
    }

    @ShellMethod("Add author for book")
    private void addAuthorForBook(@ShellOption int id_author, @ShellOption int id_book) {
        bookDao.addAuthorForBook(id_author, id_book);
    }

    @ShellMethod("Add genre for book")
    private void addGenreForBook(@ShellOption int id_genre, @ShellOption int id_book) {
        bookDao.addGenreForBook(id_genre, id_book);
    }

    @ShellMethod("Remove book")
    private void deleteBook(@ShellOption int id) {
        bookDao.deleteBook(id);
    }
}
