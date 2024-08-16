package com.shiva.stockfeed.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shiva.stockfeed.handler.FeedHandler;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Document
public class StockTradeMessage extends BaseStockMessage {

    @JsonProperty("i")
    private int tradeId;

    @JsonProperty("x")
    private String exchange;

    @JsonProperty("p")
    private Double price;

    @JsonProperty("s")
    private int tradeSize;

    @JsonProperty("c")
    private List<String> tradeConditions;

    @JsonProperty("z")
    private String tape;
    
    @Override
    public void feed(FeedHandler feedHandler) {
        feedHandler.genericFeed(this);
    }
}
