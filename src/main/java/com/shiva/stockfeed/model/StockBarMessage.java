package com.shiva.stockfeed.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shiva.stockfeed.handler.FeedHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Bars")
public class StockBarMessage implements FeedableEntity {
    

    @Id
    @JsonIgnore
    private ObjectId _id;

    @JsonProperty("T")
    private MessageType type;

    @JsonProperty("S")
    private String symbol;

    @JsonProperty("t")
    private String timestamp;

    @JsonProperty("o")
    private Double openPrice;

    @JsonProperty("h")
    private Double highPrice;

    @JsonProperty("l")
    private Double lowPrice;

    @JsonProperty("c")
    private Double closePrice;

    @JsonProperty("v")
    private Double volume;

    @Override
    public void feed(FeedHandler feedHandler) {
        feedHandler.genericFeed(this);
    }

}
