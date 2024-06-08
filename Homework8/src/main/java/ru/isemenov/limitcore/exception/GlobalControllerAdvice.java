package ru.isemenov.limitcore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.isemenov.limitcore.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleRuntimeException(RuntimeException exception) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ErrorResponseDto handleNotEnoughQuotaException(NotEnoughQuotaException exception) {
        return new ErrorResponseDto(HttpStatus.PAYLOAD_TOO_LARGE.value(), exception.getMessage());
    }
}
