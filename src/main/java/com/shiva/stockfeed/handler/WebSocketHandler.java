package com.shiva.stockfeed.handler;

import java.util.List;

import com.shiva.stockfeed.model.*;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    
    private ObjectMapper mapper;

    private FeedHandler feedHandler;

    public WebSocketHandler(ObjectMapper mapper, FeedHandler feedHandler){
        this.mapper = mapper;
        this.feedHandler = feedHandler;
    }


    @Override
    public void handleTextMessage(@NonNull WebSocketSession session,@NonNull TextMessage message) throws Exception {
        String payload = message.getPayload();

        // Process the received message
        System.out.println("Received message: " + payload);

       
        List<FeedableEntity> feedMessage = mapper.readValue(payload, new TypeReference<List<FeedableEntity>>(){});
        

        for (FeedableEntity feedableEntity : feedMessage) {
            if(feedableEntity.isFeedable()){
                feedableEntity.feed(feedHandler);
            }
        }

    }

}