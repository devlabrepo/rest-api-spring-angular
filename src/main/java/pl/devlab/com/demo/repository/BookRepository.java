package pl.devlab.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.devlab.com.demo.model.Book;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

    // deklarujemy metodę zwracającą obiekt klasy Book.
    // JpaRepository 'zajmie' się implementacją ciała tej metody:
    // zostanie automatycznie wygenrowane zapytanie SQL wyszukujące po wartości 'isbn'.
    // korzystamy z pomocniczej klasy Optional,
    // która dostarcza metody pomagające oprogramować sytuację na wypadek wystapienia null'a
    Optional<Book> findByIsbn(String isbn);

    // w interfejsie nie oznaczamy metody jako 'public', ponieważ interfejs z zasady posiada metody publiczne.

}
