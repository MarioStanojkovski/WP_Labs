package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.GenreService;
import org.springframework.ui.Model;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookReservationService bookReservationService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public BookController(BookService bookService, BookReservationService bookReservationService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.bookReservationService = bookReservationService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping
    public String getBooksPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String rating,
            Model model) {

        Double rating1;
        try {
            rating1 = Double.parseDouble(rating);
        } catch (RuntimeException e) {
            rating1 = null;
        }

        model.addAttribute("reservations", bookReservationService.findAllReservations());
        model.addAttribute("books", bookService.searchBooks(title, rating1));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.listAll());
        return "listBooks";
    }

    @GetMapping("/search_author")
    public String getpage(
            Model model,
            @RequestParam(required = false) String authorId

    ) {


        model.addAttribute("reservations", bookReservationService.findAllReservations());
        model.addAttribute("books", bookService.searchBooksbyAuthors(Long.parseLong(authorId)));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.listAll());
        return "listBooks";
    }
    @GetMapping("/search_genre")
    public String getpagege_by_genre(
            Model model,
            @RequestParam(required = false) String genreId

    ) {


        model.addAttribute("reservations", bookReservationService.findAllReservations());
        model.addAttribute("books", bookService.searchBooksbyGenres(Long.parseLong(genreId)));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.listAll());
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
            @RequestParam Long genreId,
            @RequestParam double averageRating,
            @RequestParam Long authorId
    ) {
        bookService.create(title, genreId, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam Long genreId,
            @RequestParam double averageRating,
            @RequestParam Long authorId
    ) {
        bookService.update(id, title, genreId, averageRating, authorId);
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
        model.addAttribute("genres", genreService.listAll());
        return "form";
    }


}
