package io.github.okanmenevseoglu.mancalagameapi.exception;

import io.github.okanmenevseoglu.mancalagameapi.dto.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * This class is a custom exception handler which is used to standardize the error responses by converting them to a
 * custom {@link ErrorResponse} DTO object.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Instead of exposing 500 - internal server errors to the client, logging them for further analysis.
     */
    private final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * This handler is used for handling 400 errors produced by javax validations.
     *
     * @param ex MethodArgumentNotValidException thrown by javax validations.
     * @return customized response entity object with BAD_REQUEST status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList())
                .stream().findFirst()
                .orElse("Unknown Error!");

        ErrorResponse exceptionResponse = ErrorResponse.builder()
                .timestamp(new Date().toString())
                .status(HttpStatus.BAD_REQUEST)
                .message(message)
                .build();

        return ResponseEntity.badRequest().body(exceptionResponse);
    }

    /**
     * This handler is used for handling 500 errors thrown by the application.
     *
     * @param ex any exception thrown by the application.
     * @return customized response entity object with INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllRemainingExceptions(Exception ex) {
        LOG.error(ex.getMessage(), ex);

        ErrorResponse exceptionResponse = ErrorResponse.builder()
                .timestamp(new Date().toString())
                .message("Internal Server Error")
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }
}
