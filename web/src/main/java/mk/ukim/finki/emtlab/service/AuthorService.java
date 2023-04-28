package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);

    List<Author> findAll();

    Optional<Author> save(String name, String surname, Country country);
    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, AuthorDto authorDto);
    Optional<Author> edit(Long id, String name, String surname, Country country);
    void deleteById(Long id);


}