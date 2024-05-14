package ru.isemenov.paymentscore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.isemenov.paymentscore.configuration.properties.ExecutorsProperties;
import ru.isemenov.paymentscore.dto.PaymentExecutorResponseDto;
import ru.isemenov.paymentscore.dto.ProductDto;
import ru.isemenov.paymentscore.dto.ProductResponseDto;
import ru.isemenov.paymentscore.dto.UserInfoDto;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class.getName());

    private final RestTemplate restTemplate;
    private final ExecutorsProperties properties;

    public ProductService(@Qualifier("executorClient") RestTemplate restTemplate, ExecutorsProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    /**
     * 1. Проверяем существует ли продукт
     * 2. Проверяем, достаточно ли единиц продукта для заказа
     * 3. Проверяем баланс User'а, достаточно ли средств для оплаты
     * 4. Если все пункты выполняются, производим оплату:
     *      - формируем Order
     *      - уменьшаем баланс аккаунта
     */
    @Transactional
    public PaymentExecutorResponseDto executePayment(Long userId, Long productId, Integer amount) {
        ProductDto productInfo = getProductById(productId);
        boolean isProductsEnough = productInfo.getAvailableCount() >= amount;
        UserInfoDto userInfo = getUserInfoByUserId(userId);
        boolean isBalanceEnough = productInfo.getPrice() * amount >= userInfo.getBalance();

        if (isProductsEnough && isBalanceEnough) {
            Long orderId = doPayment();
            return new PaymentExecutorResponseDto(orderId, HttpStatus.OK.toString());
        } else {
            return new PaymentExecutorResponseDto(-1L, HttpStatus.BAD_REQUEST.toString());
        }
    }

    /**
     *
     * @return возвращаем orderId, полученный от продуктового сервиса
     */
    private Long doPayment() {
        // todo направляем в продуктовый сервис пост-запрос на создание заказа (Order), уменьшаем баланс аккаунта юзера
        return 1L;
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

    public ProductDto getProductById(Long id){
        ProductDto response = restTemplate.getForObject(
                properties.getPaymentsExecutorClient().getUrl() + properties.getPaymentsExecutorClient().getGetProductByIdMethodName() + id,
                ProductDto.class
        );
        logger.info("ProductService#getProductById() получил response = {} для id = {}", response, id);
        return response;
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
