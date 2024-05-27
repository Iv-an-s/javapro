package ru.isemenov.paymentscore.configuration.exception;

public class ResourceNotEnoughException extends RuntimeException{
    public ResourceNotEnoughException(String message) {
        super(message);
    }
}
