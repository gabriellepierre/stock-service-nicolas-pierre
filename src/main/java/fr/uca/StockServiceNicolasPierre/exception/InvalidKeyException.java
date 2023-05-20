package fr.uca.StockServiceNicolasPierre.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad key request")
public class InvalidKeyException extends RuntimeException {
    public InvalidKeyException(String exception) {
        super(exception);
    }
}
