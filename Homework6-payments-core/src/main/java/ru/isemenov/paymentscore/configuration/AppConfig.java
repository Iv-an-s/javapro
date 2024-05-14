package ru.isemenov.paymentscore.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.isemenov.paymentscore.configuration.properties.ExecutorsProperties;

@Configuration
@EnableConfigurationProperties({ExecutorsProperties.class})
public class AppConfig {

    @Bean(name = "executorClient")
    public RestTemplate executorClient(
            ExecutorsProperties executorsProperties,
            RestTemplateResponseErrorHandler errorHandler){
        return new RestTemplateBuilder()
                .rootUri(executorsProperties.getPaymentsExecutorClient().getUrl())
                .setConnectTimeout(executorsProperties.getPaymentsExecutorClient().getConnectTimeout())
                .setReadTimeout(executorsProperties.getPaymentsExecutorClient().getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return mapper;
    }
}
