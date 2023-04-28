package mk.ukim.finki.emtlab.web.rest;

import mk.ukim.finki.emtlab.model.Country;
import mk.ukim.finki.emtlab.model.dto.CountryDto;
import mk.ukim.finki.emtlab.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})

public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    private List<Country> findAll() {
        return this.countryService.listAllCountries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto countryDto) {
        return this.countryService.save(countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> save(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        return this.countryService.edit(id, countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Country> deleteById(@PathVariable Long id) {
        if (this.countryService.findById(id).isEmpty()) return ResponseEntity.notFound().build();
        this.countryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}