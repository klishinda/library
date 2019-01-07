package ru.otus.library.daos.common;

import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.Library;

import java.util.List;

public interface ICommonDao {
    List<AuthorBooksCounting> countAuthorsBooks();
    List<Library> getAllLibrary();
}
