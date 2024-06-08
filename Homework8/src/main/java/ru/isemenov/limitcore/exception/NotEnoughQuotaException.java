package ru.isemenov.limitcore.exception;

public class NotEnoughQuotaException extends RuntimeException {
    public NotEnoughQuotaException(String message) {
        super(message);
    }
}
