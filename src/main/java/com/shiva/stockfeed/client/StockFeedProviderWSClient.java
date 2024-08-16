package com.shiva.stockfeed.client;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import com.shiva.stockfeed.config.AppConfig;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import java.net.URI;

@Component
public class StockFeedProviderWSClient {

    private final StandardWebSocketClient webSocketClient = new StandardWebSocketClient();

    private final WebSocketHandler webSocketHandler;

    private final AppConfig appConfig;

    public StockFeedProviderWSClient(WebSocketHandler webSocketHandler, AppConfig appConfig){
        this.webSocketHandler = webSocketHandler;
        this.appConfig = appConfig;
    }

    public void connect() {
        try {
            WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
            headers.add(appConfig.getKeyName(), appConfig.getKey());
            headers.add(appConfig.getSecretName(), appConfig.getSecret());
            webSocketClient.execute(webSocketHandler, headers ,URI.create(appConfig.getWsUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}