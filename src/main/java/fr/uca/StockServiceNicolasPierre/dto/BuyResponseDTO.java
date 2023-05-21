package fr.uca.StockServiceNicolasPierre.dto;

public class BuyResponseDTO extends StockDTO {
    private String response;

    public BuyResponseDTO(String response, String corr, String from, String to) {
        super(corr, from, to);
        this.response = response;
    }

    public BuyResponseDTO() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
