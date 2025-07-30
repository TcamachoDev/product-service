package es.tcamacho.dev.config.exceptions.exception;

public class ClientRequestException extends RuntimeException {
    public ClientRequestException(String message) {
        super(message);
    }

    public ClientRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
