package fr.uca.StockServiceNicolasPierre.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Error")
public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String exception) {
        super(exception);
    }
}
