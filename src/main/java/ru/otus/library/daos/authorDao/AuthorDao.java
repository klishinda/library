package ru.otus.library.daos.authorDao;

import ru.otus.library.model.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAuthorByName(String name);
    void addAuthor(Author author);
    void deleteAuthor(Long id);
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
}
