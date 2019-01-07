package ru.otus.library.printer;

import org.springframework.shell.table.Table;
import ru.otus.library.model.Author;
import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.Library;

import java.util.List;

public interface IResultsPrinter {
    Table printResultsAuthorBooks(List<AuthorBooksCounting> list);
    Table printResultsLibrary(List<Library> list);
    Table printAuthors(List<Author> authorByName);
}
