package com.shiva.stockfeed.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

import java.util.*;

public enum MessageType {
    
    Trades("t", true),
    Quotes("q", true),
    Bars("b", true),
    Connect("success", false),
    Subscription("subscription", false),
    Unknow("", false);

    MessageType(String code, boolean feedMessage){
        this.code = code;
        this.feedMessage = feedMessage;
    }

    @JsonValue
    @Getter
    private final String code;

    @Getter
    private final boolean feedMessage;

    @JsonCreator
    public static MessageType fromCode(String code){
        return Arrays.stream(MessageType.values())
        .filter(type -> type.getCode().equals(code))
        .findFirst()
        .orElse(MessageType.Unknow);
    }

}
