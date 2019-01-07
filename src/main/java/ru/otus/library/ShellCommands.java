package ru.otus.library;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import ru.otus.library.daos.authorDao.IAuthorDao;
import ru.otus.library.daos.common.ICommonDao;
import ru.otus.library.model.Author;
import ru.otus.library.printer.IResultsPrinter;

@ShellComponent
public class ShellCommands {
    private IAuthorDao authorDao;
    private ICommonDao commonDao;
    private IResultsPrinter printer;

    public ShellCommands(IAuthorDao authorDao, ICommonDao commonDao, IResultsPrinter printer) {
        this.authorDao = authorDao;
        this.commonDao = commonDao;
        this.printer = printer;
    }

    @ShellMethod("Counting all books in the context of the authors")
    private Table countAuthorsBooks() {
        return printer.printResultsAuthorBooks(commonDao.countAuthorsBooks());
    }

    @ShellMethod("Get all information about library")
    private Table getAllLibrary() {
        return printer.printResultsLibrary(commonDao.getAllLibrary());
    }

    @ShellMethod("Get all authors by their surname")
    private Table getAllAuthorsByName(@ShellOption String partOfSurname) {
        return printer.printAuthors(authorDao.getAuthorByName(partOfSurname));
    }

    @ShellMethod("Add new author")
    private void addAuthor(@ShellOption String surname, @ShellOption String name) {
        authorDao.addAuthor(new Author(0, surname, name));
    }

    @ShellMethod("Remove author")
    private void deleteAuthor(@ShellOption int id) {
        authorDao.deleteAuthor(id);
    }
}
