package mk.ukim.finki.wp.lab.model;

import mk.ukim.finki.wp.lab.model.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import mk.ukim.finki.wp.lab.model.enums.Genderenum;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String biography;
    @Enumerated(EnumType.STRING)
    private Genderenum genderenum;

//    @OneToMany(mappedBy = "author")
//    private List<Book> books;

    public Author(String name, String surname, String country, String biography, Genderenum genderenum) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;
        this.genderenum = genderenum;
    }

}
