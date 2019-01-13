package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    Integer id;
    String name;
    Integer pages;

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    @Override
    public final int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        if (name != null) {
            result = 31 * result + name.hashCode();
        }
        if (pages != null) {
            result = 31 * result + pages.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Book))
            return false;
        Book book = (Book)obj;
        boolean idEquals = (this.id == null && book.id == null)
                || (this.id != null && this.id.equals(book.id));
        boolean nameEquals = (this.name == null && book.name == null)
                || (this.name != null && this.name.equals(book.name));
        boolean pagesEquals = (this.pages == null && book.pages == null)
                || (this.pages != null && this.pages.equals(book.pages));
        return idEquals && nameEquals && pagesEquals;
    }
}
