package fr.uca.StockServiceNicolasPierre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uca.StockServiceNicolasPierre.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
