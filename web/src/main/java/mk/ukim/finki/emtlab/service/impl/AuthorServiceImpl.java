package mk.ukim.finki.emtlab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emtlab.model.*;
import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;
import mk.ukim.finki.emtlab.model.dto.BookDto;
import mk.ukim.finki.emtlab.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emtlab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private  final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Author> save(String name, String surname, Country country) {
        this.authorRepository.deleteByName(name);
        Author author=new Author(name, surname, country);
        this.authorRepository.save(author);
        //this.refreshMaterializedView();
        return Optional.of(author);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountryId()).get();
        this.authorRepository.deleteByName(authorDto.getName());
        Author author=new Author(authorDto.getName(),authorDto.getSurname(),country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
       Author author=this.authorRepository.findById(id).get();
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        Country country = this.countryRepository.findById(authorDto.getCountryId()).get();
       author.setCountry(country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }
    @Override
    @Transactional
    public Optional<Author> edit(Long id, String name, String surname, Country country) {
        Author author= this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        this.authorRepository.save(author);
        return Optional.of(author);

    }
    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
