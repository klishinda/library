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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    private void addBook(@ShellOption String name, @ShellOption int pages, @ShellOption String authorsId, @ShellOption String genresId) {
        Scanner authorScanner = new Scanner(authorsId);
        System.out.println(authorsId);
        System.out.println(genresId);
        List<Integer> authorList = new ArrayList<>();
        while (authorScanner.hasNextInt()) {
            System.out.println("a "+authorScanner.nextInt());
            authorList.add(authorScanner.nextInt());
        }
        Scanner genreScanner = new Scanner(genresId);
        List<Integer> genreList = new ArrayList<>();
        while (genreScanner.hasNextInt()) {
            System.out.println("g "+genreScanner.nextInt());
            genreList.add(genreScanner.nextInt());
        }
        System.out.println(authorList.size() + " " + genreList.size());

        authorList = Arrays.stream(authorsId.replace(",", " ").split("\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        genreList = Arrays.stream(genresId.replace(",", " ").split("\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(authorList.size() + " " + genreList.size());
        bookDao.addBook(new Book(name, pages), authorList, genreList);
    }

    @ShellMethod("Remove book")
    private void deleteBook(@ShellOption int id) {
        bookDao.deleteBook(id);
    }

    @ShellMethod("Get author by id")
    private Author getAuthorsById(@ShellOption int id) {
        return authorDao.getAuthorById(id);
    }

    @ShellMethod("Get all authors")
    private Table getAllAuthors() {
        return printer.printAuthors(authorDao.getAllAuthors());
    }

    @ShellMethod("Get genre by id")
    private Genre getGenreById(@ShellOption int id) {
        return genreDao.getGenreById(id);
    }

    @ShellMethod("Get all genres")
    private Table getAllGenres() {
        return printer.printGenres(genreDao.getAllGenres());
    }
}
