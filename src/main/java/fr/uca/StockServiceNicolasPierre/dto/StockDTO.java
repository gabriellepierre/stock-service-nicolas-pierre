package fr.uca.StockServiceNicolasPierre.dto;

public class StockDTO {
    private String from;
    private String to;
    private String corr;

    public StockDTO(String corr, String from, String to) {
        this.corr = corr;
        this.from = from;
        this.to = to;
    }

    public StockDTO() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCorr() {
        return corr;
    }

    public void setCorr(String corr) {
        this.corr = corr;
    }
}
