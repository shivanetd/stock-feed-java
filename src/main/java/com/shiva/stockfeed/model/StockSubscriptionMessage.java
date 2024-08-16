package com.shiva.stockfeed.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockSubscriptionMessage implements FeedableEntity {
    
    @JsonProperty("T")
    private MessageType type;

    private List<String> trades;
    private List<String> quotes;
    private List<String> bars;
}
