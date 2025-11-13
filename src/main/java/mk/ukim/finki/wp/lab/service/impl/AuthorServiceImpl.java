package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.enums.Genderenum;
import mk.ukim.finki.wp.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author create(String name, String surname, String country, String biography, Genderenum gender) {
        Author author = new Author(name, surname, country, biography, gender);
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long id, String name, String surname, String country, String biography, Genderenum gender) {


        Author author = findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));


        author.setName(name);
        author.setSurname(surname);
        author.setBiography(biography);
        author.setCountry(country);
        author.setGenderenum(gender);


        return authorRepository.save(author);


    }

    @Override
    public void delete(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        authorRepository.delete(author);

    }

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
