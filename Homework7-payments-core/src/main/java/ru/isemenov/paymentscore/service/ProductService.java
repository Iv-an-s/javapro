package ru.isemenov.paymentscore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.isemenov.paymentscore.configuration.exception.ResourceNotEnoughException;
import ru.isemenov.paymentscore.configuration.properties.ExecutorsProperties;
import ru.isemenov.paymentscore.dto.*;

import java.math.BigDecimal;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class.getName());

    private final RestTemplate restTemplate;
    private final ExecutorsProperties properties;

    public ProductService(@Qualifier("executorClient") RestTemplate restTemplate, ExecutorsProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public ProductResponseDto getProducts() {
        logger.info("ProductService#getProducts() начал работу");
        ProductResponseDto response = restTemplate.getForObject(
                properties.getPaymentsExecutorClient().getUrl() + properties.getPaymentsExecutorClient().getGetProductsMethodName(),
                ProductResponseDto.class
        );
        logger.info("ProductService#getProducts() получил response = {}", response);
        return response;
    }

    public ProductDto getProductById(Long id) {
        ProductDto response = restTemplate.getForObject(
                properties.getPaymentsExecutorClient().getUrl() + properties.getPaymentsExecutorClient().getGetProductByIdMethodName() + id,
                ProductDto.class
        );
        logger.info("ProductService#getProductById() получил response = {} для id = {}", response, id);
        return response;
    }

    @Transactional
    public PaymentExecutorResponseDto executePayment(Long userId, Long productId, Integer amount) {
        logger.info("ProductService#executePayment() начал работу");
        ProductDto productInfo = getProductById(productId);
        boolean isProductsEnough = productInfo.getAvailableCount() >= amount;
        if (!isProductsEnough) {
            throw new ResourceNotEnoughException("Запрошено недопустимое количество продукта");
        }

        UserInfoDto userInfo = getUserInfoByUserId(userId);
        boolean isBalanceEnough = productInfo.getPrice().multiply(BigDecimal.valueOf(amount))
                .compareTo(userInfo.getBalance()) <= 0;
        if (!isBalanceEnough) {
            throw new ResourceNotEnoughException("Недостаточно средств");
        }

        logger.info("ProductService#executePayment() произведена проверка на достаточность. Вызывается метод doPayment()");
        return doPayment(new PaymentExecutorRequestDto(userId, productId, amount));
    }

    private PaymentExecutorResponseDto doPayment(PaymentExecutorRequestDto requestDto) {
        logger.info("ProductService#doPayment() отправляет запрос в продуктовый сервис... ");

        PaymentExecutorResponseDto responseDto = restTemplate.postForObject(
                properties.getPaymentsExecutorClient().getUrl() + properties.getPaymentsExecutorClient().getExecutePaymentMethodName(),
                requestDto,
                PaymentExecutorResponseDto.class
        );

        logger.info("ProductService#doPayment() закончил работу. Возвращает PaymentExecutorResponseDto: " + responseDto);
        return responseDto;
    }

    private UserInfoDto getUserInfoByUserId(Long userId) {
        UserInfoDto response = restTemplate.getForObject(
                properties.getPaymentsExecutorClient().getUrl() + properties.getPaymentsExecutorClient().getGetUserInfoByUserIdMethodName() + userId,
                UserInfoDto.class
        );
        logger.info("ProductService#getUserInfoByUserId() получил response = {} для userId = {}", response, userId);
        return response;
    }
}
