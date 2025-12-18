package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.enums.Genderenum;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/author")
public class AuthorContoller {
    private final AuthorService authorService;

    public AuthorContoller(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAuthorsPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Genderenum genderenum,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Model model
    ) {


        Page<Author> authors = authorService.find(name, surname, country, genderenum, pageNum, pageSize);
        model.addAttribute("page", authors);
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("genderenum", genderenum);
        model.addAttribute("genderenums", Genderenum.values());
        return "authors";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-form")
    public String addAuthorPage(Model model) {
        model.addAttribute("genders", Arrays.stream(Genderenum.values()).toList());

        return "aform";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String saveProduct(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String country,
            @RequestParam String biography,
            @RequestParam Genderenum gender
    ) {
        authorService.create(name, surname, country, biography, gender);
        return "redirect:/author";
    }

    @PostMapping("/{id}")

    @PreAuthorize("hasRole('ADMIN')")
    public String updateAuthor(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String country,
            @RequestParam String biography,
            @RequestParam Genderenum gender


    ) {
        authorService.update(id, name, surname, country, biography, gender);
        return "redirect:/author";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id).get());
        model.addAttribute("genders", Arrays.stream(Genderenum.values()).toList());
        return "aform";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/author";
    }

}
