package fr.uca.StockServiceNicolasPierre.exception;

import me.alidg.errors.annotation.ExceptionMapping;
import org.springframework.http.HttpStatus;

@ExceptionMapping(errorCode = "400", statusCode = HttpStatus.BAD_REQUEST)
public class BookException extends RuntimeException {
    public BookException(String exception) {
        super(exception);
    }
}
