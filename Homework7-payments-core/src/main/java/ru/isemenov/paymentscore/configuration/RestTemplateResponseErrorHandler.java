package ru.isemenov.paymentscore.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.isemenov.paymentscore.configuration.exception.ResourceNotEnoughException;
import ru.isemenov.paymentscore.configuration.exception.ResourceNotFoundException;
import ru.isemenov.paymentscore.dto.ExecutorErrorDto;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (HttpStatus.NOT_FOUND.equals(response.getStatusCode())) {
            ObjectMapper objectMapper = new ObjectMapper();
            ExecutorErrorDto executorErrorDto = objectMapper.readValue(response.getBody(), ExecutorErrorDto.class);
            throw new ResourceNotFoundException(executorErrorDto.reason());
        }
        if (HttpStatus.PAYLOAD_TOO_LARGE.equals(response.getStatusCode())) {
            ObjectMapper objectMapper = new ObjectMapper();
            ExecutorErrorDto executorErrorDto = objectMapper.readValue(response.getBody(), ExecutorErrorDto.class);
            throw new ResourceNotEnoughException(executorErrorDto.reason());
        }
        if (response.getStatusCode().is4xxClientError()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ExecutorErrorDto executorErrorDto = objectMapper.readValue(response.getBody(), ExecutorErrorDto.class);
            throw new RuntimeException(executorErrorDto.reason());
        }
    }
}
