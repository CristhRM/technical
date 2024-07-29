package com.example.technicaltest.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Mono<ResponseEntity<String>> exception(Exception ex) {
        log.error("Exception", ex);
        return Mono.just(new ResponseEntity<>(ex.getMessage(), INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(ServerWebInputException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Mono<ResponseEntity<String>> serverWebInputException(ServerWebInputException ex) {
        log.error("ServerWebInputException", ex);
        return Mono.just(new ResponseEntity<>(ex.getMessage(), BAD_REQUEST));
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Mono<ResponseEntity<String>> customNotFoundException(CustomNotFoundException ex) {
        log.error("CustomNotFoundException", ex);
        return Mono.just(new ResponseEntity<>(ex.getMessage(), BAD_REQUEST));
    }
}
