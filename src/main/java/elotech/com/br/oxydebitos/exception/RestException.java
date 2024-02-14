package elotech.com.br.oxydebitos.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class RestException extends RuntimeException {

    private static final String RECORD_NOT_FOUND_MESSAGE = "Registro não encontrado";
    private static final String RECORD_CONFLICT = "Registro com conflito";

    @Serial
    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public RestException(HttpStatus status, String message) {
        super(message);
        this.httpStatus = status;
    }

    public RestException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.httpStatus = status;
    }

    public static RestException notFound() {
        return notFound(RECORD_NOT_FOUND_MESSAGE);
    }

    public static RestException notFound(String message) {
        return status(HttpStatus.NOT_FOUND, message);
    }

    public static RestException conflict() {
        return status(HttpStatus.CONFLICT, RECORD_CONFLICT);
    }

    public static RestException status(HttpStatus status, String message) {
        return new RestException(status, message);
    }

}
