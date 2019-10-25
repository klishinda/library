package ru.otus.library.printer;

import org.springframework.shell.table.Table;
import ru.otus.library.model.*;

import java.util.List;

public interface ResultsPrinter {
    Table printResultsBooksCountByAuthors(List<AuthorBooksCounting> list);
    Table printResultsAllBooks(List<Book> list);
    Table printAuthors(List<Author> authorByName);
    Table printGenres(List<Genre> list);
    Table printResultsBooksByTitle(List<Book> booksByTitle);
}
