package fr.uca.StockServiceNicolasPierre.controller;

import fr.uca.StockServiceNicolasPierre.exception.BookException;
import fr.uca.StockServiceNicolasPierre.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BooksController {

    private List<Book> books;

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
                        (int) body.get("quantity")));

        return new ResponseEntity<>(books, HttpStatus.ACCEPTED);
    }
}
