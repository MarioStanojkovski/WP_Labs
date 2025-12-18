package mk.ukim.finki.wp.lab.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private double averageRating;

    @ManyToOne
    private Genre genre;

    @Nullable
    @ManyToOne
    private Author author;

//    public boolean isDop() {
//        return dop;
//    }

    private boolean dop = false;

    public Book(String title, Genre genre, double averageRating, Author author) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;


    }


}
