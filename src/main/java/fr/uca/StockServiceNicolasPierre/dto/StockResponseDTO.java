package fr.uca.StockServiceNicolasPierre.dto;

public class StockResponseDTO extends StockDTO {
    private int stock;

    public StockResponseDTO(int stock, String corr, String from, String to) {
        super(corr, from, to);
        this.stock = stock;
    }

    public StockResponseDTO() {
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
