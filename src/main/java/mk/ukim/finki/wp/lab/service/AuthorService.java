package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.enums.Genderenum;

import java.util.List;
import java.util.Optional;

public interface AuthorService  {
    public List<Author> findAll();

    void delete(Long id);

    Optional<Author> findById(Long id);

    Author update(Long id, String name, String surname, String country, String biography, Genderenum gender);

    Author create(String name, String surname, String country, String biography, Genderenum gender);

}
