package com.shiva.stockfeed.service;

import java.util.List;

import com.shiva.stockfeed.model.*;
import com.shiva.stockfeed.repository.StockFeedSnapshotRepository;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StockFeedHandlerService extends TextWebSocketHandler {

    
    private ObjectMapper mapper;

    private StockFeedSnapshotRepository repository;

    public StockFeedHandlerService(ObjectMapper mapper, StockFeedSnapshotRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        // Process the received message
        System.out.println("Received message: " + payload);

        List<StockMessage> messages = mapper.readValue(payload, new TypeReference<List<StockMessage>>(){});

        repository.insert(messages);
    }

}