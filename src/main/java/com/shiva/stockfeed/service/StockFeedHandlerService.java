package com.shiva.stockfeed.service;

import java.util.List;

import com.shiva.stockfeed.model.*;
import com.shiva.stockfeed.repository.StockBarRepository;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StockFeedHandlerService extends TextWebSocketHandler {

    
    private ObjectMapper mapper;

    private StockBarRepository barRepository;

    public StockFeedHandlerService(ObjectMapper mapper, StockBarRepository repository){
        this.mapper = mapper;
        this.barRepository = repository;
    }


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        // Process the received message
        System.out.println("Received message: " + payload);

        List<BaseStockMessage> messages = mapper.readValue(payload, new TypeReference<List<BaseStockMessage>>(){});

        //TODO:- Add a design pattern to handle all messages in better way 
        for (BaseStockMessage baseStockMessage : messages) {
            if(baseStockMessage.getType().equals("b")){
                List<StockBarMessage> barMessages = mapper.readValue(payload, new TypeReference<List<StockBarMessage>>(){});
                barRepository.insert(barMessages);
            }
        }
    }

}