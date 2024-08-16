package com.shiva.stockfeed.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "feed-provider")
@Getter
@Setter
public class AppConfig {
    
    private String wsUrl;

    private String key;

    private String keyName;

    private String secret;

    private String secretName;

    private String tickers;

}
