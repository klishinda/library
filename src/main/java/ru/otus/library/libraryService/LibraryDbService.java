package ru.otus.library.libraryService;

import ru.otus.library.model.Book;

import java.util.List;

public interface LibraryDbService {
    void addBook(Book book, List<Integer> idAuthors, List<Integer> idGenres);
}
