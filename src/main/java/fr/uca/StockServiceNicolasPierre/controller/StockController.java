package fr.uca.StockServiceNicolasPierre.controller;

import fr.uca.StockServiceNicolasPierre.exception.BookNotFoundException;
import fr.uca.StockServiceNicolasPierre.exception.InternalErrorException;
import fr.uca.StockServiceNicolasPierre.service.BookService;
import fr.uca.StockServiceNicolasPierre.entity.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class StockController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/stock/{isbn}")
    public ResponseEntity<Integer> getBookStock(@PathVariable String isbn) {
        try {
            return bookService.getBookStock(isbn);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Book not found");
        } catch (Exception e) {
            throw new InternalErrorException("Internal error: " + e.getMessage());
        }
    }

    // @ResponseBody
    // @ExceptionHandler()
    // @ResponseStatus(HttpStatus.NOT_FOUND)

    @PostMapping("/stock")
    public void postBookStock(@RequestBody Book newBook) {
        try {
            this.bookService.postBookStock(newBook);
        } catch (Exception e) {
            throw new InternalErrorException("Internal error: " + e.getMessage());
        }

    }

    // @PutMapping("/stock/{isbn}/{quantity}")

}
