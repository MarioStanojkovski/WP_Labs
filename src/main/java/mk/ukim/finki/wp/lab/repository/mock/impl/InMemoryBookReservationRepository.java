package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.boodstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.mock.BookReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {
    @Override
    public BookReservation save(BookReservation reservation) {
        DataHolder.reservations.removeIf(t -> t.getBookTitle().equals(reservation.getBookTitle()));
        DataHolder.reservations.add(reservation);
        return reservation;
    }

    @Override
    public List<BookReservation> find() {
        return DataHolder.reservations;
    }

    @Override
    public void deleteLast() {
        DataHolder.reservations.removeLast();
    }
}
