package com.shiva.stockfeed.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shiva.stockfeed.handler.FeedHandler;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "T")
@JsonSubTypes({
    @JsonSubTypes.Type(value = StockBarMessage.class, name = "b"),
    @JsonSubTypes.Type(value = StockTradeMessage.class, name = "t"),
    @JsonSubTypes.Type(value = StockQuoteMessage.class, name = "q"),
    @JsonSubTypes.Type(value = BaseStockMessage.class, name = "success")
})
public interface FeedableEntity {
    String getType();
    
    void feed(FeedHandler feedAgregator);
    
    default boolean isFeedable(){
        return getType().equals("b") 
            || getType().equals("t") 
            || getType().equals("q");
    }
}