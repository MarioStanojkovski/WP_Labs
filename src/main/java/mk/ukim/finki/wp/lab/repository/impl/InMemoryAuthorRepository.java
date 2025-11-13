package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.boodstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository implements AuthorRepository {
    @Override
    public void delete(Author author) {
        DataHolder.authors.remove(author);
    }

    @Override
    public Author save(Author author) {
        DataHolder.authors.removeIf(b -> b.getId().equals(author.getId()));
        DataHolder.authors.add(author);
        return author;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return DataHolder.authors.stream().filter(author -> author.getId().equals(id)).findFirst();
    }

    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }
}
