package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor_Id(Long authorId);
    List<Book> findAllByGenre_Id(Long authorId);
    List<Book> findAllByAverageRating(Double rating);
    List<Book> findAllByTitle(String text);
    List<Book> findAllByTitleAndAverageRating(String text,Double rating);



}

