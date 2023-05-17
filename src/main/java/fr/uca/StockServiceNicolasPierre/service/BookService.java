package fr.uca.StockServiceNicolasPierre.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import fr.uca.StockServiceNicolasPierre.entity.Book;
import fr.uca.StockServiceNicolasPierre.exception.BookNotFoundException;
import fr.uca.StockServiceNicolasPierre.exception.InternalErrorException;
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

    public ResponseEntity<Integer> getBookStock(@PathVariable String isbn) {
        Optional<Book> searchedBook = getBook(isbn);

        if (searchedBook.isPresent()) {
            return ResponseEntity.ok().body(searchedBook.get().getQuantity());
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    public void postBookStock(@RequestBody Book newBook) {
        // We check if the new book is not in db already
        if (!getBook(newBook.getIsbn()).isPresent()) {
            this.bookRepository.save(new Book(newBook.getIsbn(), newBook.getQuantity()));
        } else {
            // throw BAD_REQUEST exception because isbn is already in DB
            // ? put new book instead of creating new one
        }

        // TODO throw error when the isbn is already in DB
    }

    // public Book putBook(@RequestBody Book newBook) {
    // this.bookRepository.save(new Book(book.getIsbn(), quantity));

    // TODO Modify quantity field (decrease) when the buy request is settled
    // TODO Modify quantity field (increase) when the wholesaler is available

    // }

    // public void update(Long id, Account account) {
    // Account_Entities account_entities = account_repository.getOnes(id);
    // if (account_entities == null)
    // throw new EntityNotFoundException();
    // account_repository.create(account);
    // }
}
