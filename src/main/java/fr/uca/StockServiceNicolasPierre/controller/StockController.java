package fr.uca.StockServiceNicolasPierre.controller;

import fr.uca.StockServiceNicolasPierre.exception.BookNotFoundException;
import fr.uca.StockServiceNicolasPierre.model.Book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    private List<Book> books;

    public StockController() {
        books = new ArrayList<>();
        books.add(new Book("2", 2, "Harry Potter et le prince de sang mêlé", "J.K.Rowling"));
        books.add(new Book("8", 4, "Harry Potter et la chambre des secrets", "J.K.Rowling"));
        books.add(new Book("7", 0, "One Piece Tome 103", "Eichiro Oda"));
        books.add(new Book("1", 8, "Une vie", "Maupassant"));
    }

    @GetMapping("/stock")
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Integer> getBookStock(@RequestParam(value = "isbn") String isbn) {
        Book searchedBook = books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().get();
        int stock = searchedBook.getQuantity();
        // if (stock == 0) {
        // throw new BookNotFoundException("Nous n'avez pas ce livre en stock");
        // }
        return new ResponseEntity<Integer>(stock, HttpStatus.OK);
    }

    // @PutMapping("/book")
    // public Book putBook(@RequestParam(value="isbn") UUID isbn, ) {

    // TODO Modify quantity field (decrease) when the buy request is settled
    // TODO Modify quantity field (increase) when the wholesaler is available

    // }
}
