package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.enums.Genderenum;
import mk.ukim.finki.wp.lab.service.AuthorService;
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
    public String getAuthorsPage(@RequestParam(required = false) String error, Model model) {

        model.addAttribute("authors", authorService.findAll());
        return "authors";
    }

    @GetMapping("/add-form")
    public String addAuthorPage(Model model) {
        model.addAttribute("genders", Arrays.stream(Genderenum.values()).toList());

        return "aform";
    }


        @PostMapping
    public String saveProduct(
                @RequestParam String name,
                @RequestParam String surname,
                @RequestParam String country,
                @RequestParam String biography,
                @RequestParam Genderenum gender
    ) {
        authorService.create(name,surname,country,biography,gender);
        return "redirect:/author";
    }
    @PostMapping("/{id}")

    public String updateAuthor(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String country,
            @RequestParam String biography,
            @RequestParam Genderenum gender



            ) {
        authorService.update(id, name, surname, country, biography,gender);
        return "redirect:/author";
    }
    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id).get());
        model.addAttribute("genders", Arrays.stream(Genderenum.values()).toList());
        return "aform";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/author";
    }

}
