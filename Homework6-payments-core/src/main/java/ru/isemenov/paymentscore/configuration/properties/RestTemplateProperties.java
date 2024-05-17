package ru.isemenov.paymentscore.configuration.properties;

import java.time.Duration;


/**
 * настройки, в том числе технические, для нашего RestTemplate
 */
public class RestTemplateProperties {
    private String url;
    private String getProductsMethodName;
    private String getProductByIdMethodName;
    private String executePaymentMethodName;
    private String getUserInfoByUserIdMethodName;
    private Duration connectTimeout;
    private Duration readTimeout;


    public String getGetUserInfoByUserIdMethodName() {
        return getUserInfoByUserIdMethodName;
    }

    public void setGetUserInfoByUserIdMethodName(String getUserInfoByUserIdMethodName) {
        this.getUserInfoByUserIdMethodName = getUserInfoByUserIdMethodName;
    }

    public String getGetProductByIdMethodName() {
        return getProductByIdMethodName;
    }

    public void setGetProductByIdMethodName(String getProductByIdMethodName) {
        this.getProductByIdMethodName = getProductByIdMethodName;
    }

    public String getUrl() {
        return url;
    }

    public String getGetProductsMethodName() {
        return getProductsMethodName;
    }

    public String getExecutePaymentMethodName() {
        return executePaymentMethodName;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setGetProductsMethodName(String getProductsMethodName) {
        this.getProductsMethodName = getProductsMethodName;
    }

    public void setExecutePaymentMethodName(String executePaymentMethodName) {
        this.executePaymentMethodName = executePaymentMethodName;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }


}
