package fr.uca.StockServiceNicolasPierre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @Column
    private String isbn;

    @Column
    private int quantity;

    public Book(String isbn, int quantity) {
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public Book() {
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}
