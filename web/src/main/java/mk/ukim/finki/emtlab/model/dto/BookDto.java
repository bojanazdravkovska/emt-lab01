package mk.ukim.finki.emtlab.model.dto;

import lombok.Data;
import mk.ukim.finki.emtlab.model.Category;

@Data
public class BookDto {
    private String name;
    private Category category;
    private Long authorId;
    private int availableCopies;

    public BookDto(String name, int availableCopies, Category category, Long authorId) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }

    public BookDto() {
    }
}