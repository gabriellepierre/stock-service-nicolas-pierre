package fr.uca.StockServiceNicolasPierre.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import fr.uca.StockServiceNicolasPierre.dto.StockResponseDTO;
import fr.uca.StockServiceNicolasPierre.entity.Book;
import fr.uca.StockServiceNicolasPierre.exception.BadQuantityRequestException;
import fr.uca.StockServiceNicolasPierre.exception.BookNotFoundException;
import fr.uca.StockServiceNicolasPierre.repository.BookRepository;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public java.util.List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(String isbn) {
        Optional<Book> searchedBook = this.bookRepository.findById(isbn);
        return searchedBook;
    }

    public ResponseEntity<StockResponseDTO> getBookStock(String isbn, String corr, String from, String to) {
        Optional<Book> searchedBook = getBook(isbn);

        if (searchedBook.isPresent()) {
            StockResponseDTO res = new StockResponseDTO(searchedBook.get().getQuantity(), corr, from, to);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    public void postBookStock(@RequestBody Book newBook) {
        // We check if the new book is not in db already
        if (!getBook(newBook.getIsbn()).isPresent()) {
            this.bookRepository.save(new Book(newBook.getIsbn(), newBook.getQuantity()));
        } else {
        }
    }

    // Methods to buy a book

    private void destockBook(String isbn,
            int quantity) {
        // We check if the new book is registered db
        if (getBook(isbn).isPresent()) {
            Book bookToUpdate = getBook(isbn).get();
            int oldStock = bookToUpdate.getQuantity();
            bookToUpdate.setQuantity(oldStock - quantity);
            this.bookRepository.save(bookToUpdate);

            // WholesalerResponseDto response = new WholesalerResponseDto(
            // "wholesale order sent",
            // this.SERVICE_ID,
            // this.SHOPPING_ID,
            // updateCorr(corr, this.SERVICE_ID, this.SHOPPING_ID));

            // return ResponseEntity.ok().body(response);
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    public void buyBook(String isbn,
            int quantity, String corr, String from, String to) {
        if (quantity >= 0) {
            destockBook(isbn, quantity);
        } else {
            throw new BadQuantityRequestException("Not enough stock.");
        }
    }
}
