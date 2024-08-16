package com.shiva.stockfeed.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shiva.stockfeed.model.*;

public interface StockBarRepository extends MongoRepository<StockBarMessage, String> {

    
    
}