package ru.isemenov.paymentscore.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations.executors")
public class ExecutorsProperties {
    private final RestTemplateProperties paymentsExecutorClient;

    public RestTemplateProperties getPaymentsExecutorClient() {
        return paymentsExecutorClient;
    }

    // Внимание!!! Имя параметра важно! Нельзя указать произвольное client, execClient, или что-то иное
    // произвольное. Должно быть точно как название поля, здесь - paymentsExecutorClient!
    @ConstructorBinding
    public ExecutorsProperties(RestTemplateProperties paymentsExecutorClient) {
        this.paymentsExecutorClient = paymentsExecutorClient;
    }
}
