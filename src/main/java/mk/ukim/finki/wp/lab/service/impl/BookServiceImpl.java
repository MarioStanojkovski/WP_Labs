package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.Genre;
import mk.ukim.finki.wp.lab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.GenreNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GenreRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        if ((text == null || text.isEmpty()) && (rating == null || rating.isNaN())) {
            return listAll();
        } else if (text == null || text.isEmpty()) {
            return bookRepository.findAllByAverageRating(rating);
        } else if (rating == null || rating.isNaN()) {
            return bookRepository.findAllByTitle(text);
        } else return this.bookRepository.findAllByTitleAndAverageRating(text, rating);
    }

    @Override
    public List<Book> searchBooksbyAuthors(Long authorId) {
        if ((authorId == null)) {
            return listAll();
        } else return this.bookRepository.findAllByAuthor_Id(authorId);
    }

    @Override
    public Book create(String title, Long genreId, double averageRating, Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new GenreNotFoundException(genreId));
        Book book = new Book(title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> searchBooksbyGenres(Long genreId) {
        if ((genreId == null)) {
            return listAll();
        } else return this.bookRepository.findAllByGenre_Id(genreId);
    }


    @Override
    public Book update(Long id, String title, Long genreId, double averageRating, Long authorId) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new GenreNotFoundException(genreId));
        book.setTitle(title);
        book.setAuthor(author);
        book.setAverageRating(averageRating);
        book.setGenre(genre);
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.deleteById(id);
    }
}
