package mk.ukim.finki.emtlab.web.rest;

import mk.ukim.finki.emtlab.model.Book;
import mk.ukim.finki.emtlab.model.dto.BookDto;
import mk.ukim.finki.emtlab.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})

public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    private List<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(Book -> ResponseEntity.ok().body(Book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto BookDto) {
        return this.bookService.save(BookDto)
                .map(Book -> ResponseEntity.ok().body(Book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto BookDto) {
        return this.bookService.edit(id, BookDto)
                .map(Book -> ResponseEntity.ok().body(Book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/mark/{id}")
    private ResponseEntity<Book> mark(@PathVariable Long id) {
        return this.bookService.markAsTaken(id)
                .map(Book -> ResponseEntity.ok().body(Book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
