package com.shiva.stockfeed.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseStockMessage implements FeedableEntity{
    
    @Id
    @JsonIgnore
    private String _id;

    @JsonProperty("T")
    private MessageType type;

    @JsonProperty("S")
    private String symbol;

    @JsonProperty("t")
    private String timestamp;

    @JsonProperty("msg")
    private String message;
}
