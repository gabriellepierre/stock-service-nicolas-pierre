package fr.uca.StockServiceNicolasPierre.service;

import org.springframework.stereotype.Service;

import fr.uca.StockServiceNicolasPierre.entity.Book;
import fr.uca.StockServiceNicolasPierre.exception.BookNotFoundException;
import fr.uca.StockServiceNicolasPierre.exception.InternalErrorException;
import fr.uca.StockServiceNicolasPierre.repository.BookRepository;

@Service
public class BookService {

    private BookRepository bookRepository;

    public java.util.List<Book> getBooks() throws InternalErrorException, BookNotFoundException {
        return bookRepository.findAll();
    }

    // public void createBook(Book book) {
    // bookRepository.createBook(book);
    // }

    // public void update(Long id, Account account) {
    // Account_Entities account_entities = account_repository.getOnes(id);
    // if (account_entities == null)
    // throw new EntityNotFoundException();
    // account_repository.create(account);
    // }

}
