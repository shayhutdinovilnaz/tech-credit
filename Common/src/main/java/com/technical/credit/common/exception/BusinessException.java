package com.technical.credit.common.exception;

/**
 * Exception is thrown when wrong in anything business logic.
 *
 * @author ilnaz-92@yandex.ru
 * Created on 05.04.2020
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
