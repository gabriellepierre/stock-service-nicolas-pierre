package fr.uca.StockServiceNicolasPierre.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid quantity")
public class BadQuantityRequestException extends RuntimeException {
    public BadQuantityRequestException(String exception) {
        super(exception);
    }
}
