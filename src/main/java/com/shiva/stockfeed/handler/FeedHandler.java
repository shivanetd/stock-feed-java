package com.shiva.stockfeed.handler;

import com.shiva.stockfeed.model.*;

public interface FeedHandler {
    void genericFeed(StockBarMessage message);
    void genericFeed(StockTradeMessage message);
    void genericFeed(StockQuoteMessage message);
}