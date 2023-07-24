package infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class WebServiceException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;

    public WebServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
