package mk.ukim.finki.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long authorId) {
        super(String.format("Author with id %d does not exist.", authorId));
    }
}
