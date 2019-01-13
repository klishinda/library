package ru.otus.library.libraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.library.daos.authorDao.AuthorDao;
import ru.otus.library.daos.bookDao.BookDao;
import ru.otus.library.daos.genreDao.GenreDao;
import ru.otus.library.model.Author;
import ru.otus.library.model.Book;
import ru.otus.library.model.BookList;
import ru.otus.library.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryDbServiceImpl implements LibraryDbService{
    private AuthorDao authorDao;
    private GenreDao genreDao;
    private BookDao bookDao;

    @Autowired
    public LibraryDbServiceImpl(AuthorDao authorDao, GenreDao genreDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.bookDao = bookDao;
    }

    public void addBook(Book book, List<Integer> idAuthors, List<Integer> idGenres) {
        BookList bookList = new BookList();
        bookList.setBook(book);
        bookList.setAuthors(idAuthors.stream().map(Author::new).collect(Collectors.toSet()));
        bookList.setGenres(idGenres.stream().map(Genre::new).collect(Collectors.toSet()));
        bookDao.addBook(bookList);
    }
}
