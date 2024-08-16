package com.shiva.stockfeed.model;

import com.shiva.stockfeed.handler.FeedHandler;

public interface FeedableEntity {
    String getType();
    
    void feed(FeedHandler feedAgregator);
    
    default boolean isFeedable(){
        return getType().equals("b") 
            || getType().equals("t") 
            || getType().equals("q");
    }
}