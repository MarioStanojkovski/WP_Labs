package mk.ukim.finki.wp.lab.boodstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.model.enums.Genderenum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = null;
    public static List<User> users = null;


    @PostConstruct
    public void init() {
        authors = new ArrayList<>();
        authors.add(new Author("Mario", "Stanojkovski", "Macedonia", "Student", Genderenum.valueOf("MALE")));
        authors.add(new Author("Darko", "Sasanski", "Macedonia", "Teacher-assistant", Genderenum.valueOf("MALE")));
        authors.add(new Author("Ana", "Todorovska", "Macedonia", "MCS-Teacher-assistant", Genderenum.valueOf("FEMALE")));
        books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            books.add(new Book("Book" + i, "g" + i, 5. + i, authors.get(0)));
        }
        books.add(new Book("Book11", "g2", 5. + 1, DataHolder.authors.get(1)));
        books.add(new Book("Book12", "g3", 5. + 1, DataHolder.authors.get(2)));
        users = new ArrayList<>();
        users.add(new User("elena.atanasoska", "ea", "Elena", "Atanasoska"));
        users.add(new User("ana.todorovska", "at", "Ana", "Todorovska"));
        users.add(new User("mario", "ms", "Mario", "Stanojkovski"));


    }

}
