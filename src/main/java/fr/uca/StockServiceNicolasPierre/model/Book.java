package fr.uca.StockServiceNicolasPierre.model;

public class Book {
    private String isbn;
    private int quantity;
    private String titre;
    private String auteur;

    public Book(String isbn, int quantity, String titre, String auteur) {
        this.isbn = isbn;
        this.quantity = quantity;
        this.titre = titre;
        this.auteur = auteur;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
