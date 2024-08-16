package com.shiva.stockfeed.handler;

import java.util.Arrays;
import java.util.List;

import com.shiva.stockfeed.model.*;
import com.shiva.stockfeed.model.request.SubscriptionRequest;
import com.shiva.stockfeed.config.*;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    
    private final ObjectMapper mapper;

    private final FeedHandler feedHandler;

    private final AppConfig appConfig;

    public WebSocketHandler(ObjectMapper mapper, FeedHandler feedHandler, AppConfig appConfig){
        this.mapper = mapper;
        this.feedHandler = feedHandler;
        this.appConfig = appConfig;
    }


    @Override
    public void handleTextMessage(@NonNull WebSocketSession session,@NonNull TextMessage message) throws Exception {
        String payload = message.getPayload();
        // Process the received message
        System.out.println("Received message: " + payload);
        List<FeedableEntity> feedMessage = mapper.readValue(payload, new TypeReference<List<FeedableEntity>>(){});
        
        for (FeedableEntity feedableEntity : feedMessage) {
            if(isSubscribiedEntity(feedableEntity)){
                feedableEntity.feed(feedHandler);
            }

            if(feedableEntity.getType() == MessageType.Connect){
                BaseStockMessage wsMessage = (BaseStockMessage) feedableEntity;
                if(wsMessage.getMessage().equals("authenticated")){
                    session.sendMessage(subscriptionRequest());
                }
            }
        }
    }

    private boolean isSubscribiedEntity(FeedableEntity entity){
        return 
            Arrays.stream(MessageType.values())
                .filter( type -> type.isSubscribed())
                .anyMatch( type -> type == entity.getType());
    }

    private TextMessage subscriptionRequest() throws JsonProcessingException{
        
        SubscriptionRequest request = new SubscriptionRequest();

        request.setAction("subscribe");
        List<String> tickersToSubscribe = Arrays.stream(appConfig.getTickers().split(" "))
            .filter(t -> !t.isEmpty())
            .map(String::toUpperCase)
            .toList();
        request.setTrades(tickersToSubscribe);
        request.setBars(tickersToSubscribe);
        request.setTrades(tickersToSubscribe);

        String req = mapper.writeValueAsString(request);
        return new TextMessage(req);
    }

}