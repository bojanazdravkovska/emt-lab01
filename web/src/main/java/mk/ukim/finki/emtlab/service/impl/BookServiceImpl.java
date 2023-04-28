package mk.ukim.finki.emtlab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.emtlab.model.Category;
import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dto.BookDto;
import mk.ukim.finki.emtlab.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.BookRepository;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Integer availableCopies, Category category, Author author) {
        this.bookRepository.deleteByName(name);
        Book book = new Book(name, availableCopies, category, author);
        this.bookRepository.save(book);
        //this.refreshMaterializedView();
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author=this.authorRepository.findById(bookDto.getAuthorId()).get();
       // this.bookRepository.deleteByName(bookDto.getName());
        Book book=new Book(bookDto.getName(),bookDto.getAvailableCopies(),bookDto.getCategory(),author);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book=this.bookRepository.findById(id).get();
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        Author author=this.authorRepository.findById(bookDto.getAuthorId()).get();
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Integer availableCopies, Category category, Author author) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(name);
        book.setAvailableCopies(availableCopies);
        book.setCategory(category);
        book.setAuthor(author);
        this.bookRepository.save(book);
        return Optional.of(book);

    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
    public Optional<Book> markAsTaken(Long id) {

        Book book= this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Integer availableCopies = book.getAvailableCopies();
        availableCopies=availableCopies-1;
        if( availableCopies <= 0 )
        {
            availableCopies=0;
        }
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);



    }
}
