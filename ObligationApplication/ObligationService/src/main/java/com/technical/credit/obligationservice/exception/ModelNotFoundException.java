package com.technical.credit.obligationservice.exception;

/**
 * Exception is thrown when model isn't found in system.
 *
 * @author ilnaz-92@yandex.ru
 * Created on 05.04.2020
 */
public class ModelNotFoundException extends BusinessException {
    public ModelNotFoundException() {
    }

    public ModelNotFoundException(String message) {
        super(message);
    }

    public ModelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

