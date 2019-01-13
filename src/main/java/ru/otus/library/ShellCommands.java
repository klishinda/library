package ru.otus.library;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import ru.otus.library.libraryService.LibraryDbService;
import ru.otus.library.model.Author;
import ru.otus.library.model.Genre;
import ru.otus.library.printer.ResultsPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@ShellComponent
public class ShellCommands {
    private ResultsPrinter printer;
    private LibraryDbService libraryDbService;

    public ShellCommands(ResultsPrinter printer, LibraryDbService libraryDbService) {
        this.printer = printer;
        this.libraryDbService = libraryDbService;
    }

    @ShellMethod("Counting all books in the context of the authors")
    private Table getBooksCountByAuthors() {
        return printer.printResultsBooksCountByAuthors(libraryDbService.getBooksCountByAuthors());
    }

    @ShellMethod("Get book by its title")
    private Table getBooksByTitle(@ShellOption String partOfTitle) {
        return printer.printResultsBooksByTitle(libraryDbService.getBooksByTitle(partOfTitle));
    }

    @ShellMethod("Get all information about library")
    private Table getAllBooks() {
        return printer.printResultsAllBooks(libraryDbService.getAllBooks());
    }

    @ShellMethod("Get all authors by their surname")
    private Table getAllAuthorsByName(@ShellOption String partOfSurname) {
        return printer.printAuthors(libraryDbService.getAllAuthorsByName(partOfSurname));
    }

    @ShellMethod("Add new author")
    private void addAuthor(@ShellOption String surname, @ShellOption String name) {
        libraryDbService.addAuthor(new Author(surname, name));
    }

    @ShellMethod("Remove author")
    private void deleteAuthor(@ShellOption Long id) {
        libraryDbService.deleteAuthor(id);
    }

    @ShellMethod("Add new genre")
    private void addGenre(@ShellOption String name) {
        libraryDbService.addGenre(new Genre(name));
    }

    @ShellMethod("Remove genre")
    private void deleteGenre(@ShellOption Long id) {
        libraryDbService.deleteGenre(id);
    }

    @ShellMethod("Add new book")
    private void addBook(@ShellOption String name, @ShellOption Long pages, @ShellOption String authorsId, @ShellOption String genresId) {
        Scanner authorScanner = new Scanner(authorsId);
        List<Long> authorList = new ArrayList<>();
        while (authorScanner.hasNextInt()) {
            authorList.add(authorScanner.nextLong());
        }
        Scanner genreScanner = new Scanner(genresId);
        List<Long> genreList = new ArrayList<>();
        while (genreScanner.hasNextInt()) {
            genreList.add(genreScanner.nextLong());
        }

        authorList = Arrays.stream(authorsId.replace(",", " ").split("\\s"))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        genreList = Arrays.stream(genresId.replace(",", " ").split("\\s"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        libraryDbService.addBook(name, pages, authorList, genreList);
    }

    @ShellMethod("Remove book")
    private void deleteBook(@ShellOption Long id) {
        libraryDbService.deleteBook(id);
    }

    @ShellMethod("Get author by id")
    private Author getAuthorsById(@ShellOption Long id) {
        return libraryDbService.getAuthorById(id);
    }

    @ShellMethod("Get all authors")
    private Table getAllAuthors() {
        return printer.printAuthors(libraryDbService.getAllAuthors());
    }

    @ShellMethod("Get genre by id")
    private Genre getGenreById(@ShellOption Long id) {
        return libraryDbService.getGenreById(id);
    }

    @ShellMethod("Get all genres")
    private Table getAllGenres() {
        return printer.printGenres(libraryDbService.getAllGenres());
    }
}
