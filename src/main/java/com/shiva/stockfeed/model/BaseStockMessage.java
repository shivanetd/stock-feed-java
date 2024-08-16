package com.shiva.stockfeed.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseStockMessage {
    
    @JsonProperty("T")
    private String type;

    @JsonProperty("S")
    private String symbol;

    @Id
    @JsonIgnore
    private String _id;


    @JsonProperty("t")
    private String timestamp;

    @JsonProperty("msg")
    private String message;
}
