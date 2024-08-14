package com.shiva.stockfeed.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import java.net.URI;

@Component
public class StockFeedProviderWSClient {

    private final StandardWebSocketClient webSocketClient = new StandardWebSocketClient();

    @Value("${stockprovider.wsapi.url}")
    private String wsUrl;

    @Value("${stockprovider.api.key}")
    private String apiKey;

    @Value("${stockprovider.api.keyName}")
    private String apiKeyHeader;

    @Value("${stockprovider.api.secret}")
    private String apiSecret;

    @Value("${stockprovider.api.secretName}")
    private String apiSecretHeader;

    private final WebSocketHandler webSocketHandler;

    public StockFeedProviderWSClient(WebSocketHandler webSocketHandler){
        this.webSocketHandler = webSocketHandler;
    }

    public void connect() {
        try {
            WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
            headers.add(apiKeyHeader, apiKey);
            headers.add(apiSecretHeader, apiKeyHeader);
            webSocketClient.execute(webSocketHandler, headers ,URI.create(wsUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}