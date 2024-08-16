package com.shiva.stockfeed.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shiva.stockfeed.handler.FeedHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collation = "Quotes")
public class StockQuoteMessage implements FeedableEntity {

    @Id
    @JsonIgnore
    private String _id;
    
    @JsonProperty("T")
    private String type;

    @JsonProperty("S")
    private String symbol;

    @JsonProperty("t")
    private String timestamp;

    @JsonProperty("ax")
    private String askExchange;

    @JsonProperty("bx")
    private String bidExchange;

    @JsonProperty("ap")
    private Double askPrice;

    @JsonProperty("bp")
    private Double bidPrice;

    @JsonProperty("as")
    private Integer askTradeSize;

    @JsonProperty("bs")
    private Integer bidTradeSize;

    @JsonProperty("c")
    private List<String> tradeConditions;

    @JsonProperty("z")
    private String tape;

    @Override
    public void feed(FeedHandler feedHandler) {
        feedHandler.genericFeed(this);
    }
}
