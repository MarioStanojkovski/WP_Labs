package mk.ukim.finki.wp.lab.model.exceptions;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long BookId) {
        super(String.format("Book with id %d does not exist.", BookId));
    }
}
