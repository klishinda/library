package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Long id;
    private String surname;
    private String name;

    public Author(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public Author(Long id) {
        this.id = id;
    }

    @Override
    public final int hashCode() {
        int result = 17;
        if (id != null) {
            result = 31 * result + id.hashCode();
        }
        if (surname != null) {
            result = 31 * result + surname.hashCode();
        }
        if (name != null) {
            result = 31 * result + name.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Author))
            return false;
        Author author = (Author)obj;
        boolean idEquals = (this.id == null && author.id == null)
                || (this.id != null && this.id.equals(author.id));
        boolean nameEquals = (this.name == null && author.name == null)
                || (this.name != null && this.name.equals(author.name));
        boolean surnameEquals = (this.surname == null && author.surname == null)
                || (this.surname != null && this.surname.equals(author.surname));
        return idEquals && nameEquals && surnameEquals;
    }
}