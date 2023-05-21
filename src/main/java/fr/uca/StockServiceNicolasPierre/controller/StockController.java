package fr.uca.StockServiceNicolasPierre.controller;

import fr.uca.StockServiceNicolasPierre.exception.BadQuantityRequestException;
import fr.uca.StockServiceNicolasPierre.exception.BookNotFoundException;
import fr.uca.StockServiceNicolasPierre.exception.InternalErrorException;
import fr.uca.StockServiceNicolasPierre.service.BookService;
import fr.uca.StockServiceNicolasPierre.dto.BuyResponseDTO;
import fr.uca.StockServiceNicolasPierre.dto.StockResponseDTO;
import fr.uca.StockServiceNicolasPierre.entity.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class StockController {

    @Autowired
    private BookService bookService;

    private final String SERVICE_ID = "Stock";
    private final String SHOPPING_ID = "S";

    private String updateCorr(String initialCorr, String from, String to) {
        return initialCorr + ";" + from + "-" + to;
    }

    @GetMapping("/")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/stock/{isbn}")
    public ResponseEntity<StockResponseDTO> getBookStock(@PathVariable String isbn, @RequestParam String corr,
            @RequestParam String from,
            @RequestParam String to) {
        try {
            return bookService.getBookStock(isbn, updateCorr(corr, this.SERVICE_ID, this.SHOPPING_ID), this.SERVICE_ID,
                    this.SHOPPING_ID);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Book not found");
        } catch (InternalErrorException e) {
            throw new InternalErrorException("Internal error");
        } catch (Exception e) {
            throw new InternalErrorException("Internal error" + e.getMessage());
        }
    }

    @PostMapping("/stock")
    public void postBookStock(@RequestBody Book newBook) {
        try {
            this.bookService.postBookStock(newBook);
        } catch (Exception e) {
            throw new InternalErrorException("Internal error: " + e.getMessage());
        }
    }

    @PutMapping("/buy/{isbn}/{quantity}")
    public ResponseEntity<BuyResponseDTO> buyBooks(
            @PathVariable String isbn,
            @PathVariable int quantity,
            @RequestParam String corr,
            @RequestParam String from,
            @RequestParam String to) {
        try {
            return bookService.buyBook(isbn, quantity, updateCorr(corr, this.SERVICE_ID, this.SHOPPING_ID),
                    this.SERVICE_ID,
                    this.SHOPPING_ID);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Book not found.");
        } catch (BadQuantityRequestException e) {
            throw new BadQuantityRequestException("Not enough stock.");
        } catch (InternalErrorException e) {
            throw new InternalErrorException("Internal error occured.");
        } catch (Exception e) {
            throw new InternalErrorException("Internal error.");
        }
    }

}
