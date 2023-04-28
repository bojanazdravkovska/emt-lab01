package mk.ukim.finki.emtlab.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
//    @JoinColumn(name = "country_id")
    private Country country;

    public Author(String name, String surname, Country country) {
        //this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Author() {

    }
}
