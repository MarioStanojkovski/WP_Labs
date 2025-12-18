package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.enums.Genderenum;
import mk.ukim.finki.wp.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static mk.ukim.finki.wp.lab.service.FieldFilterSpecification.*;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Override
    public Page<Author> find(String name, String surname, String country, Genderenum genderenum, Integer pageNum, Integer pageSize) {
        Specification<Author> specification = Specification.allOf(
                filterContainsText(Author.class, "name", name),
                filterContainsText(Author.class, "surname", surname),
                filterContainsText(Author.class, "country", country),
                filterEqualsV(Author.class, "genderenum", genderenum)
        );

        return this.authorRepository.findAll(
                specification,
                PageRequest.of(pageNum - 1, pageSize));
    }

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

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
        List<Book> books = bookRepository.findAllByAuthor_Id(id);
        books.forEach(book -> {
            book.setAuthor(null);
            bookRepository.save(book);
        });

        authorRepository.delete(author);

    }

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
