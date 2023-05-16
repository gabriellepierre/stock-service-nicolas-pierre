package fr.uca.StockServiceNicolasPierre.controller;

import fr.uca.StockServiceNicolasPierre.exception.BookNotFoundException;
import fr.uca.StockServiceNicolasPierre.repository.BookRepository;
import fr.uca.StockServiceNicolasPierre.entity.Book;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {

    private BookRepository bookRepository;

    public StockController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/stock/{isbn}")
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Integer> getBookStock(@PathVariable String isbn) {
        Optional<Book> searchedBook = this.bookRepository.findById(isbn);

        if (searchedBook.isPresent()) {
            return ResponseEntity.ok().body(searchedBook.get().getQuantity());
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    @PostMapping("/stock")
    public void postBookStock(@RequestBody Book newBook) {
        this.bookRepository.save(new Book(newBook.getIsbn(), newBook.getQuantity()));

        // TODO throw error when the isbn is already in DB
    }

    // @PutMapping("/stock/{isbn}/{quantity}")
    // public Book putBook(@PathVariable String isbn) {
    // this.bookRepository.save(new Book(book.getIsbn(), quantity));

    // TODO Modify quantity field (decrease) when the buy request is settled
    // TODO Modify quantity field (increase) when the wholesaler is available

    // }
}
