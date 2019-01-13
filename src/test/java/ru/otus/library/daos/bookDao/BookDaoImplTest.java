package ru.otus.library.daos.bookDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.library.daos.authorDao.AuthorDaoImpl;
import ru.otus.library.daos.genreDao.GenreDaoImpl;
import ru.otus.library.libraryService.LibraryDbService;
import ru.otus.library.libraryService.LibraryDbServiceImpl;
import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.Book;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = NONE)
@Import({BookDaoImpl.class, GenreDaoImpl.class, AuthorDaoImpl.class, LibraryDbServiceImpl.class})

public class BookDaoImplTest {

    @Autowired
    private LibraryDbService libraryDbService;

    @Test
    public void getBooksByTitle() {
        addNewBook();
        List<Book> firstBook = libraryDbService.getBooksByTitle("Test book");
        assertThat(firstBook.size()).isEqualTo(1);
        List<Book> secondBook = libraryDbService.getBooksByTitle("10 негритят");
        assertThat(secondBook.size()).isEqualTo(1);
    }

    @Test
    public void getBooksCountByAuthors() {
        int correctTests = 0;
        addNewBook();
        List<AuthorBooksCounting> list = libraryDbService.getBooksCountByAuthors();
        for (AuthorBooksCounting authorCounting : list) {
            if (authorCounting.getSurname().equals("ЛОНДОН") && authorCounting.getName().equals("ДЖЕК")) {
                assertThat(authorCounting.getCounting()).isEqualTo(2);
                correctTests++;
            }
            if (authorCounting.getSurname().equals("ПИЗ") && authorCounting.getName().equals("АЛЛАН")) {
                assertThat(authorCounting.getCounting()).isEqualTo(2);
                correctTests++;
            }
        }
        assertThat(correctTests).isEqualTo(2);
    }

    @Test
    public void getAllBooks() {
        int correctTests = 0;
        addNewBook();
        List<Book> allBooks = libraryDbService.getAllBooks();
        assertThat(allBooks.size()).isEqualTo(8);
        for (Book book : allBooks) {
            if (book.getId() == 1) {
                assertThat(book.getName()).isEqualTo("Восточный экспресс");
                assertThat(book.getGenres().size()).isEqualTo(1);
                correctTests++;
            }
            if (book.getId() == 5) {
                assertThat(book.getName()).isEqualTo("Язык телодвижений");
                assertThat(book.getAuthors().size()).isEqualTo(2);
                correctTests++;
            }
        }
        assertThat(correctTests).isEqualTo(2);
    }

    @Test
    public void deleteBook() {
        addNewBook();
        libraryDbService.deleteBook(6L);
        List<Book> allBooks = libraryDbService.getAllBooks();
        assertThat(allBooks.size()).isEqualTo(7);
        List<Book> books = libraryDbService.getBooksByTitle("10 негритят");
        assertThat(books.size()).isEqualTo(0);
    }

    private void addNewBook() {
        List<Long> genres = new ArrayList<>();
        genres.add(1L);
        genres.add(2L);
        List<Long> authors = new ArrayList<>();
        authors.add(5L);
        authors.add(6L);
        libraryDbService.addBook("Test book", 1000L, authors, genres);
    }
}
