package com.shiva.stockfeed.model;

import com.shiva.stockfeed.handler.FeedHandler;

public interface FeedableMessage {
    void feed(FeedHandler feedAgregator);
}