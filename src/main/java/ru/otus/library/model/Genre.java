package ru.otus.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    Integer id;
    String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Integer id) {
        this.id = id;
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
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Genre))
            return false;
        Genre genre = (Genre)obj;
        boolean idEquals = (this.id == null && genre.id == null)
                || (this.id != null && this.id.equals(genre.id));
        boolean nameEquals = (this.name == null && genre.name == null)
                || (this.name != null && this.name.equals(genre.name));
        return idEquals && nameEquals;
    }
}
