package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> listAll();

    Genre create(String name, String description);

    Genre update(
            Long id,
            String name,
            String description
    );

    Optional<Genre> findById(Long id);

    void delete(Long id);
}
