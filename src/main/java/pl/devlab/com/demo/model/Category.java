package pl.devlab.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data //adnotacja pochodzi z biblioteki Lombok.
@Entity
@Table(name = "categories") //ustakamy ręcznie nazwę dla tabeli
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // konfigurujemy nazwę kolumny w tabeli po stronie bazy danych oraz parametry takie jak nie pusty i unikalny.
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category",
            cascade = {
                    CascadeType.DETACH, // odłączanie kolekcji
                    CascadeType.MERGE,  // aktualizacja encji
                    CascadeType.PERSIST,// włączanie nowej encji do kontekstu
                    CascadeType.REFRESH // odświeżanie stanu encji
                    // CascadeType.REMOVE // usuwanie encji - nie dodajemy, gdyż nie chcemy usuwać książek
                    // w przypadku usunięcia kategorii.
            }
    )
    private Set<Book> books = new HashSet<>();

}
