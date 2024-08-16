package com.shiva.stockfeed.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shiva.stockfeed.model.*;

public interface StockQuoteRepository extends MongoRepository<StockQuoteMessage, String> {

    
    
}