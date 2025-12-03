package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {



    void delete(Author author);

    public List<Author> findAll();
    Optional<Author> findById(Long id);

    Author save(Author author);
}
