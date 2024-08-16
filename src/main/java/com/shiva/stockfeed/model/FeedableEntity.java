package com.shiva.stockfeed.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shiva.stockfeed.handler.FeedHandler;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "T", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = StockBarMessage.class, name = "b"),
    @JsonSubTypes.Type(value = StockTradeMessage.class, name = "t"),
    @JsonSubTypes.Type(value = StockQuoteMessage.class, name = "q"),
    @JsonSubTypes.Type(value = BaseStockMessage.class, name = "success"),
    @JsonSubTypes.Type(value = StockSubscriptionMessage.class, name = "subscription")
})
public interface FeedableEntity {

    MessageType getType();
    
    default void feed(FeedHandler feedAgregator){

    }
    
}