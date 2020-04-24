package com.technical.credit.webapp.controller;

import com.technical.credit.obligationservice.exception.ModelNotFoundException;
import com.technical.credit.webapp.formatter.RequestExceptionHandlerFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class AbstractController {

    @ExceptionHandler(ModelNotFoundException.class)
    public default ResponseEntity<Map<String, Object>> handleIllegalArgumentException(final ModelNotFoundException exception, final HttpServletRequest request) {
        final Map<String, Object> responseBody = RequestExceptionHandlerFormatter.format(exception, request, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(final Exception exception, final HttpServletRequest request) {
        final Map<String, Object> responseBody = RequestExceptionHandlerFormatter.format(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
