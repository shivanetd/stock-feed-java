package com.shiva.stockfeed.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
public class StockFeedHandlerService extends AbstractWebSocketHandler {

    @Override
    public void handleMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        // Process the received message
        System.out.println("Received message: " + payload);
    }

}