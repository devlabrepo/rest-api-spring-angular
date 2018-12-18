package pl.devlab.com.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private String author;

//    public Book(String title, String isbn, String author) {
//        this.title = title;
//        this.isbn = isbn;
//        this.author = author;
//    }
}
