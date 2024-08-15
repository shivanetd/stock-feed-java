package com.shiva.stockfeed.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMessage {

    @JsonProperty("T")
    private String type;
    
    @JsonProperty("i")
    private Integer tradeId;

    @JsonProperty("S")
    private String symbol;

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

    @JsonProperty("x")
    private String exchange;

    @JsonProperty("ax")
    private String askExchange;

    @JsonProperty("bx")
    private String bidExchange;

    @JsonProperty("p")
    private Double price;

    @JsonProperty("ap")
    private Double askPrice;

    @JsonProperty("bp")
    private Double bidPrice;

    @JsonProperty("s")
    private Integer tradeSize;

    @JsonProperty("as")
    private Integer askTradeSize;

    @JsonProperty("bs")
    private Integer bidTradeSize;

    @JsonProperty("t")
    private String timestamp;

    @JsonProperty("c")
    private List<String> tradeConditions;

    @JsonProperty("z")
    private String tape;
}
