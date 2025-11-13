package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();

    List<Book> searchBooks(String text, Double rating);

    Book create(
            String title,
            String genre,
            double averageRating,
            Long authorId
    );

Book update(
        Long id,
        String title,
        String genre,
        double averageRating,
        Long authorId
    );
    Optional<Book> findById(Long id);

    void delete(Long id);
}
