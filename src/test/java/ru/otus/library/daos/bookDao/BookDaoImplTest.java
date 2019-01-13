package ru.otus.library.daos.bookDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.library.daos.authorDao.AuthorDao;
import ru.otus.library.daos.authorDao.AuthorDaoImpl;
import ru.otus.library.daos.genreDao.GenreDao;
import ru.otus.library.daos.genreDao.GenreDaoImpl;
import ru.otus.library.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
/*@SpringBootTest(properties={
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"}
)*/
@JdbcTest
@AutoConfigureTestDatabase(replace = NONE)
@Import({BookDaoImpl.class, GenreDaoImpl.class, AuthorDaoImpl.class})

public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void getBooksByTitle() {
        addNewBook();
        List<Book> firstBook = bookDao.getBooksByTitle("Test book");
        assertThat(firstBook.size()).isEqualTo(1);
        List<Book> secondBook = bookDao.getBooksByTitle("10 негритят");
        assertThat(secondBook.size()).isEqualTo(1);
    }

    @Test
    public void getBooksCountByAuthors() {
        int correctTests = 0;
        addNewBook();
        List<AuthorBooksCounting> list = bookDao.getBooksCountByAuthors();
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
        List<BookList> allBooks = bookDao.getAllBooks();
        assertThat(allBooks.size()).isEqualTo(8);
        for (BookList book : allBooks) {
            if (book.getBook().getId() == 1) {
                assertThat(book.getBook().getName()).isEqualTo("Восточный экспресс");
                assertThat(book.getGenres().size()).isEqualTo(1);
                correctTests++;
            }
            if (book.getBook().getId() == 5) {
                assertThat(book.getBook().getName()).isEqualTo("Язык телодвижений");
                assertThat(book.getAuthors().size()).isEqualTo(2);
                correctTests++;
            }
        }
        assertThat(correctTests).isEqualTo(2);
    }

    @Test
    public void deleteBook() {
        addNewBook();
        bookDao.deleteBook(6);
        List<BookList> allBooks = bookDao.getAllBooks();
        assertThat(allBooks.size()).isEqualTo(7);
        List<Book> books = bookDao.getBooksByTitle("10 негритят");
        assertThat(books.size()).isEqualTo(0);
    }

    private void addNewBook() {
        Book book = new Book("Test book", 1000);
        Set<Genre> genres = new HashSet<>();
        genres.add(genreDao.getGenreById(1));
        genres.add(genreDao.getGenreById(2));
        Set<Author> authors = new HashSet<>();
        authors.add(authorDao.getAuthorById(5));
        authors.add(authorDao.getAuthorById(6));
        bookDao.addBook(new BookList(book, authors, genres));
    }
}
