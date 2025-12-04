package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();

    List<Book> searchBooks(String text, Double rating);
    List<Book> searchBooksbyAuthors (Long authorId);



    Book create(String title, Long genreId, double averageRating, Long authorId);

    Book update(
        Long id,
        String title,
        Long genreId,
        double averageRating,
        Long authorId
    );
    Optional<Book> findById(Long id);

    void delete(Long id);
    List<Book> searchBooksbyGenres (Long genreId);
    Page<Book> find(String title,Long authorId, Long genreId, Integer pageNum, Integer pageSize);

}
