package mk.ukim.finki.emtlab.service;
import mk.ukim.finki.emtlab.model.Category;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, Integer availableCopies, Category category, Author author);
    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id,BookDto bookDto);



    Optional<Book> edit(Long id, String name, Integer availableCopies, Category category, Author author);

    void deleteById(Long id);
    Optional<Book> markAsTaken(Long id);
}
