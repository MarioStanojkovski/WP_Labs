package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookReservation")

public class BookReservationController {

    private final BookService bookService;
    private final BookReservationService bookReservationService;

    public BookReservationController(BookService bookService, BookReservationService bookReservationService) {
        this.bookService = bookService;
        this.bookReservationService = bookReservationService;
    }


    @PostMapping
    public String reserveBook(
            HttpServletRequest request,
            Model model,
            @RequestParam("book") Long selectedBookId,
            @RequestParam("readerName") String readerName,
            @RequestParam("readerAddress") String readerAddress,
            @RequestParam("numCopies") int numCopies
    ) {

        Book selectedBook = bookService.findById(selectedBookId);
        bookReservationService.placeReservation(selectedBook.getTitle(),readerName,readerAddress,numCopies,selectedBook.getAuthor());
        model.addAttribute("book", selectedBook);
        model.addAttribute("numCopies", numCopies);
        model.addAttribute("readerAddress", readerAddress);
        model.addAttribute("ipAddress",request.getRemoteAddr());
        model.addAttribute("readerName",readerName);
        return "reservationConfirmation";
    }
}