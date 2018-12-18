package pl.devlab.com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.devlab.com.demo.model.Book;
import pl.devlab.com.demo.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {

    // @Autowired
    private BookRepository bookRepository;

    //@Autowired // tutaj nie jest wymagana adnotacja - zależność wstrzykiwana jest automatycznie
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("books")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // W klamrach {} umieszczamy nazwę zmiennej podanej podczas zapytania.
    // Adnotacja @PathVariable zmapuje tą wartość na argument o nazwie 'isbn' w metodzie.
    // Następnie używamy @RequestBody, aby zmapować przychodzący obiekt JSON na obiekt klasy Book.
    // Użyję klasy ResponseEntity dla wygodniejszego zarządzania zwracanymi statusami.

    @PutMapping("books/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book book) {

        // staramy się zwrócić wynik metody do obiektu Optional<Book>:
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);

        // sprawdzamy, czy obiekt jest null'em. Jeśli nie jest - zwracamy nowy obiekt z wartościami pochodzącymi z obiektu 'book'
        if (optionalBook.isPresent()) {
            // metoda get() wywołana na obiekcie Optional powoduje 'odpakowanie' obiektu do postaci pierwotnej, w tym przypadku do Book.
            optionalBook.get().setAuthor(book.getAuthor());
            optionalBook.get().setTitle(book.getTitle());
            optionalBook.get().setIsbn(book.getIsbn());

            return new ResponseEntity<>(bookRepository.save(optionalBook.get()), HttpStatus.OK); // 200
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }


    @DeleteMapping("books/{isbn}")
    public ResponseEntity<Book> deleteBook(@PathVariable String isbn) {

        // tak samo jak przy metodzie aktualizującej rekord, najpierw szukamy rekordu i zapisujemy do obiektu.
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);

        if (optionalBook.isPresent()) {
            // usuwamy obiekt, jeżeli istnieje.
            bookRepository.delete(optionalBook.get());
            return new ResponseEntity<>(HttpStatus.OK); //200
        }
        // zwracamy status 404 jeżeli obiekt o podanym 'isbn' nie istnieje.
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
    }

}
