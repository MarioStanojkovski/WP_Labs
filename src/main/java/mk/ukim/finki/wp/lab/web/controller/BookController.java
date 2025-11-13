package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.ui.Model;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookReservationService bookReservationService;
    private final AuthorService authorService;

    public BookController(BookService bookService, BookReservationService bookReservationService, AuthorService authorService) {
        this.bookService = bookService;
        this.bookReservationService = bookReservationService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {

        model.addAttribute("reservations", bookReservationService.findAllReservations());
        model.addAttribute("books",  bookService.listAll());
        return "listBooks";
    }

    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "form";
    }
    @PostMapping
    public String saveProduct(
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam double averageRating,
            @RequestParam Long authorId
    ) {
        bookService.create(title,genre,averageRating,authorId);
        return "redirect:/books";
    }
    @PostMapping("/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam double averageRating,
            @RequestParam Long authorId
    ) {
        bookService.update(id, title, genre, averageRating, authorId);
        return "redirect:/books";
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }


    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id).get());
        model.addAttribute("authors", authorService.findAll());
        return "form";
    }



}
