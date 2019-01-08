package ru.otus.library.daos.bookDao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.Book;
import ru.otus.library.model.BookInfo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties={
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class BookDaoImplTest {

    @Autowired
    public BookDao bookDao;

    @Test
    @Transactional
    @Rollback(true)
    public void addBook() {
        Book book = new Book("test book", 66);
        bookDao.addBook(book);
        List<BookInfo> b = bookDao.getAllBooks();
        bookDao.addAuthorForBook(5, 6);
        assertThat(b.get(1).getBookTitle()).isEqualTo("test book");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addAuthorForBook() {
        bookDao.addAuthorForBook(5, 6);
        List<BookInfo> b = bookDao.getAllBooks();
        assertThat(b.get(0).getAuthorName()).isEqualTo("АГАТА КРИСТИ, ДЖЕК ЛОНДОН");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void addGenreForBook() {
        bookDao.addGenreForBook(11, 6);
        List<BookInfo> b = bookDao.getAllBooks();
        for (BookInfo bb : b) {
            System.out.println(bb.getBookTitle() + " " + bb.getGenre() + " " + bb.getAuthorName() + " " + bb.getNumberOfPages());
        }
        assertThat(b.get(0).getGenre()).isEqualTo("ДЕТЕКТИВ, ФИЛОСОФИЯ");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteBook() {
        bookDao.deleteBook(6);
        List<BookInfo> b = bookDao.getAllBooks();
        assertThat(b.get(0).getGenre()).isNotEqualTo("10 негритят");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void getBooksCountByAuthors() {
        bookDao.addAuthorForBook(3, 6);
        List<AuthorBooksCounting> b = bookDao.getBooksCountByAuthors();
        assertThat(b.get(0).getCounting()).isEqualTo(1);
    }
}
