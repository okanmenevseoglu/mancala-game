package io.github.okanmenevseoglu.mancalagameapi.dto.response;

import io.github.okanmenevseoglu.mancalagameapi.exception.CustomExceptionHandler;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * This is the response DTO object for error handling used by {@link CustomExceptionHandler} class.
 */
@Getter
@Builder
public class ErrorResponse {

    /**
     * A final string used to store exception time.
     */
    private final String timestamp;

    /**
     * A final {@link HttpStatus} variable used to store HTTP status.
     */
    private final HttpStatus status;

    /**
     * A final string used to store the exception message.
     */
    private final String message;

    /**
     * Constructor for all args.
     *
     * @param timestamp a string time
     * @param status    an http status enum value
     * @param message   a string error message
     */
    public ErrorResponse(String timestamp, HttpStatus status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }
}
