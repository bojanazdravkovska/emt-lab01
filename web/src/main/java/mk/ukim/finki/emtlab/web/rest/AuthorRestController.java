package mk.ukim.finki.emtlab.web.rest;

import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.Author;
import mk.ukim.finki.emtlab.model.dto.AuthorDto;
import mk.ukim.finki.emtlab.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})


public class AuthorRestController {

    private  final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    private List<Author> findAll() {
        return  this.authorService .findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id) {
        return  this.authorService .findById(id)
                .map(Author -> ResponseEntity.ok().body(Author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto AuthorDto) {
        return this.authorService.save(AuthorDto)
                .map(Author -> ResponseEntity.ok().body(Author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> save(@PathVariable Long id, @RequestBody AuthorDto AuthorDto) {
        return this.authorService.edit(id, AuthorDto)
                .map(Author -> ResponseEntity.ok().body(Author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        if(this.authorService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        this.authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
