package ru.isemenov.limitcore.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.properties")
public class ApplicationProperties {

    private BigDecimal defaultLimit;
}
