package fr.uca.StockServiceNicolasPierre.controller;

import fr.uca.StockServiceNicolasPierre.exception.BookException;
import fr.uca.StockServiceNicolasPierre.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BooksController {

    private List<Book> books;

    public BooksController() {
        books = new ArrayList<>();
        books.add(new Book("2", 2, "Harry Potter et le prince de sang mêlé", "J.K.Rowling"));
        books.add(new Book("8", 4, "Harry Potter et la chambre des secrets", "J.K.Rowling 2"));
        books.add(new Book("7", 0, "One Piece Tome 103", "Eichiro Oda"));
        books.add(new Book("1", 8, "Une vie", "Maupassant"));
        books.add(new Book("3", 6, "Le dev pour les nuls", "Emile Zola"));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/book")
    @ExceptionHandler(BookException.class)
    public ResponseEntity<Book> getBookStock(@RequestParam(value = "isbn") String isbn) {
        Book searchedBook = books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().get();
        return new ResponseEntity<Book>(searchedBook, HttpStatus.OK);
    }

    @DeleteMapping("/books/book")
    public ResponseEntity<List<Book>> deleteBook(@RequestParam(value = "isbn") String isbn) {
        books.remove(
                books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().get());

        return new ResponseEntity<>(books, HttpStatus.ACCEPTED);
    }

    @PostMapping("/books")
    public ResponseEntity<List<Book>> postBook(@RequestBody Map<String, Object> body) {
        books.add(
                new Book(
                        (String) body.get("isbn"),
                        (int) body.get("quantity"),
                        (String) body.get("titre"),
                        (String) body.get("auteur")));

        return new ResponseEntity<>(books, HttpStatus.ACCEPTED);
    }
}
