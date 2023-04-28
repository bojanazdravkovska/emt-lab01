package mk.ukim.finki.emtlab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.Category;
import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.BookRepository;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializier {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializier(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 5; i++) {
            countryRepository.save(new Country("Country " + i, "Contient: " + i));
            List<Country> countries = countryRepository.findAll();

            authorRepository.save(new Author("AuthorName: " + i, "Author surname: " + i, countries.get(i - 1)));
            List<Author> authors = authorRepository.findAll();

            bookRepository.save(new Book("BookName: " + i, 10, Category.DRAMA, authors.get(i - 1)));

        }
    }
}