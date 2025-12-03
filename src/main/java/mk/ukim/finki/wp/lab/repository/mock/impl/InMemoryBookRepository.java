package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.boodstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.mock.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public Book save(Book book) {
        DataHolder.books.removeIf(b -> b.getId().equals(book.getId()));
        DataHolder.books.add(book);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public void detele(Book book) {
        DataHolder.books.remove(book);
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream().filter(p -> p.getAverageRating() >= rating && p.getTitle().equals(text)).toList();
    }

    @Override
    public List<Book> searchBooksByText(String text) {
        return DataHolder.books.stream().filter(p -> p.getTitle().contains(text)).toList();
    }

    @Override
    public List<Book> searchBooksByRating(Double r) {
        return DataHolder.books.stream().filter(p -> p.getAverageRating() >= r).toList();
    }
}
