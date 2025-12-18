package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/books","/"})
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
    @GetMapping()
    public String getBooks(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long genreId,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Model model
    ) {
        if (error != null) {
            model.addAttribute("error", error);
        }

        Page<Book> books = bookService.find(title, authorId, genreId, pageNum, pageSize);
        model.addAttribute("page", books);
        model.addAttribute("title", title);
        model.addAttribute("genreId", genreId);
        model.addAttribute("authorId", authorId);
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.listAll());

        return "listBooks";
    }
//
//    @GetMapping("/search_author")
//    public String getpage(
//            Model model,
//            @RequestParam(required = false) String authorId
//
//    ) {
//
//
//        model.addAttribute("reservations", bookReservationService.findAllReservations());
//        model.addAttribute("books", bookService.searchBooksbyAuthors(Long.parseLong(authorId)));
//        model.addAttribute("authors", authorService.findAll());
//        model.addAttribute("genres", genreService.listAll());
//        return "listBooks";
//    }
//
//    @GetMapping("/search_genre")
//    public String getpagege_by_genre(
//            Model model,
//            @RequestParam(required = false) String genreId
//
//    ) {
//
//
//        model.addAttribute("reservations", bookReservationService.findAllReservations());
//        model.addAttribute("books", bookService.searchBooksbyGenres(Long.parseLong(genreId)));
//        model.addAttribute("authors", authorService.findAll());
//        model.addAttribute("genres", genreService.listAll());
//        return "listBooks";
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-form")
    public String addBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.listAll());
        return "form";
    }
    @PreAuthorize("hasRole('ADMIN')")
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


    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/like-status/{id}")
    public String Like_status(@PathVariable Long id) {
        bookService.like(id);
        return "redirect:/books";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.listAll());
        return "form";
    }


}
