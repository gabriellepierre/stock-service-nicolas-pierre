package fr.uca.StockServiceNicolasPierre.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad book request")
public class BookException extends RuntimeException {
    public BookException(String exception) {
        super(exception);
    }
}
