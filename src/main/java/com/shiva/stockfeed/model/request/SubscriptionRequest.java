package com.shiva.stockfeed.model.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequest {
    private String action;
    private List<String> trades;
    private List<String> quotes;
    private List<String> bars;
}
