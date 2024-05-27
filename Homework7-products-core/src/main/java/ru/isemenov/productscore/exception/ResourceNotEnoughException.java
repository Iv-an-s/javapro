package ru.isemenov.productscore.exception;

public class ResourceNotEnoughException extends RuntimeException {
    public ResourceNotEnoughException(String message) {
        super(message);
    }
}
