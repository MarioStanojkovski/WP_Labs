package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Genre;
import mk.ukim.finki.wp.lab.model.exceptions.GenreNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.GenreRepository;
import mk.ukim.finki.wp.lab.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> listAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre create(String name, String description) {
        Genre genre = new Genre(name, description);
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Long genreId, String name, String description) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new GenreNotFoundException(genreId));
        genre.setName(name);
        genre.setDescription(description);
        return genreRepository.save(genre);
    }

    @Override
    public Optional<Genre> findById(Long genreId) {
        return genreRepository.findById(genreId);
    }

    @Override
    public void delete(Long genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new GenreNotFoundException(genreId));
        genreRepository.delete(genre);
    }
}
