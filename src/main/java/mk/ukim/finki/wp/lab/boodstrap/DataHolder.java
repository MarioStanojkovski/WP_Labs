package mk.ukim.finki.wp.lab.boodstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.model.enums.Genderenum;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GenreRepository;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = null;
    public static List<Genre> genres = null;
    public static List<User> users = null;

    public DataHolder(BookRepository bookRepository, UserRepository userRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }


    @PostConstruct
    public void init() {
        authors = new ArrayList<>();
        authors.add(new Author("Mario", "Stanojkovski", "Macedonia", "Student", Genderenum.valueOf("MALE")));
        authors.add(new Author("Darko", "Sasanski", "Macedonia", "Teacher-assistant", Genderenum.valueOf("MALE")));
        authors.add(new Author("Ana", "Todorovska", "Macedonia", "MCS-Teacher-assistant", Genderenum.valueOf("FEMALE")));

        if (authorRepository.findAll().isEmpty()) {
            authorRepository.saveAll(authors);
        }

        genres = new ArrayList<>();
        genres.add(new Genre( "Science", "blabla"));
        genres.add(new Genre( "Action", "bambam"));
        genres.add(new Genre( "Comedy", "hahaha"));

        if (genreRepository.findAll().isEmpty()) {
            genreRepository.saveAll(genres);
        }
        books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            books.add(new Book("Book" + i, genres.get(1), 5. + i, authorRepository.findById(1L).get()));
        }
        books.add(new Book("Book11", genres.get(2), 5. + 1, authorRepository.findById(1L).get()));
        books.add(new Book("Book12", genres.get(1), 5. + 1, authorRepository.findById(1L).get()));
        if (bookRepository.findAll().isEmpty()) {
            bookRepository.saveAll(books);
        }


        users = new ArrayList<>();
        users.add(new User("elena.atanasoska", "ea", "Elena", "Atanasoska"));
        users.add(new User("darko.sasanski", "ds", "Darko", "Sasanski"));
        users.add(new User("ana.todorovska", "at", "Ana", "Todorovska"));
        users.add(new User("mario", "ms", "Mario", "Stanojkovski"));
        if (userRepository.findAll().isEmpty()) {
            userRepository.saveAll(users);
        }
    }

}
