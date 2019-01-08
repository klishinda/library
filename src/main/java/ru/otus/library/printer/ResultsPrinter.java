package ru.otus.library.printer;

import org.springframework.shell.table.Table;
import ru.otus.library.model.Author;
import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.BookInfo;

import java.util.List;

public interface ResultsPrinter {
    Table printResultsBooksCountByAuthors(List<AuthorBooksCounting> list);
    Table printResultsAllBooks(List<BookInfo> list);
    Table printAuthors(List<Author> authorByName);
}
