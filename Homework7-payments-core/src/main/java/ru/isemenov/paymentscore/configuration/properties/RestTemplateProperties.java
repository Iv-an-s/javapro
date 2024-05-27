package ru.isemenov.paymentscore.configuration.properties;

import lombok.Data;

import java.time.Duration;


/**
 * настройки, в том числе технические, для нашего RestTemplate
 */
@Data
public class RestTemplateProperties {
    private String url;
    private String getProductsMethodName;
    private String getProductByIdMethodName;
    private String executePaymentMethodName;
    private String getUserInfoByUserIdMethodName;
    private Duration connectTimeout;
    private Duration readTimeout;
}
