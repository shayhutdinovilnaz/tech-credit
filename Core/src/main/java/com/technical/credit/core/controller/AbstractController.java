package com.technical.credit.core.controller;

import com.technical.credit.core.exception.ModelNotFoundException;
import com.technical.credit.core.formatter.RequestExceptionHandlerFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(final ModelNotFoundException exception, final HttpServletRequest request) {
        LOG.error("Something wrong is happened in request process. Required model was not found in system.", exception);
        final Map<String, Object> responseBody = RequestExceptionHandlerFormatter.format(exception, request, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(final Exception exception, final HttpServletRequest request) {
        LOG.error("Something wrong is happened in request process. Request", exception);
        final Map<String, Object> responseBody = RequestExceptionHandlerFormatter.format(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
