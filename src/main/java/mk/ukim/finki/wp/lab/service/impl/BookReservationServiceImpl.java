package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.mock.BookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReservationServiceImpl implements BookReservationService {

    private final BookReservationRepository bookReservationRepository;

    public BookReservationServiceImpl(BookReservationRepository bookReservationRepository) {
        this.bookReservationRepository = bookReservationRepository;
    }


    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, double numberOfCopies, Author author) {
        if(bookTitle==null || readerAddress==null || readerName==null)
        {
            throw new RuntimeException("Cannot place a bookreservation with null values");
        };
        if(bookReservationRepository.find().size()>3)
        {
            bookReservationRepository.deleteLast();
        }
        BookReservation reservation = new BookReservation(bookTitle, readerName, readerAddress, numberOfCopies,author);
        return bookReservationRepository.save(reservation);
    }

    @Override
    public List<BookReservation> findAllReservations() {
        return  bookReservationRepository.find();
    }
}
