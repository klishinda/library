package ru.otus.library.printer;

import org.springframework.shell.table.*;
import org.springframework.stereotype.Service;
import ru.otus.library.model.Author;
import ru.otus.library.model.AuthorBooksCounting;
import ru.otus.library.model.BookInfo;
import ru.otus.library.model.Genre;

import java.util.List;

@Service
public class ResultsPrinterImpl implements ResultsPrinter {

    public Table printResultsBooksCountByAuthors(List<AuthorBooksCounting> list) {
        String[][] table = new String[list.size()+1][3];
        TableModel model = new ArrayTableModel(table);
        TableBuilder tableBuilder = new TableBuilder(model);

        table[0][0] = "ФАМИЛИЯ АВТОРА";
        table[0][1] = "ИМЯ АВТОРА";
        table[0][2] = "КОЛИЧЕСТВО ВЫПУЩЕННЫХ КНИГ";
        for (int i = 0; i < list.size(); i++) {
            table[i+1][0] = list.get(i).getSurname();
            table[i+1][1] = list.get(i).getName();
            table[i+1][2] = String.valueOf(list.get(i).getCounting());
        }

        return tableBuilder.addFullBorder(BorderStyle.fancy_light).build();
    }

    public Table printResultsAllBooks(List<BookInfo> list) {
        String[][] table = new String[list.size()+1][4];
        TableModel model = new ArrayTableModel(table);
        TableBuilder tableBuilder = new TableBuilder(model);

        table[0][0] = "НАЗВАНИЕ КНИГИ";
        table[0][1] = "ЖАНР(Ы)";
        table[0][2] = "ИМЯ АВТОРА(ОВ)";
        table[0][3] = "КОЛИЧЕСТВО СТРАНИЦ";
        for (int i = 0; i < list.size(); i++) {
            table[i+1][0] = list.get(i).getBookTitle();
            table[i+1][1] = list.get(i).getGenre();
            table[i+1][2] = list.get(i).getAuthorName();
            table[i+1][3] = String.valueOf(list.get(i).getNumberOfPages());
        }

        return tableBuilder.addFullBorder(BorderStyle.fancy_light).build();
    }

    public Table printAuthors(List<Author> list) {
        String[][] table = new String[list.size()+1][2];
        TableModel model = new ArrayTableModel(table);
        TableBuilder tableBuilder = new TableBuilder(model);

        table[0][0] = "ID";
        table[0][1] = "АВТОР";
        for (int i = 0; i < list.size(); i++) {
            table[i+1][0] = String.valueOf(list.get(i).getId());
            table[i+1][1] = list.get(i).getName() + " " + list.get(i).getSurname();
        }

        return tableBuilder.addFullBorder(BorderStyle.fancy_light).build();
    }

    public Table printGenres(List<Genre> list) {
        String[][] table = new String[list.size()+1][2];
        TableModel model = new ArrayTableModel(table);
        TableBuilder tableBuilder = new TableBuilder(model);

        table[0][0] = "ID";
        table[0][1] = "ЖАНР";
        for (int i = 0; i < list.size(); i++) {
            table[i+1][0] = String.valueOf(list.get(i).getId());
            table[i+1][1] = list.get(i).getName();
        }

        return tableBuilder.addFullBorder(BorderStyle.fancy_light).build();
    }
}
