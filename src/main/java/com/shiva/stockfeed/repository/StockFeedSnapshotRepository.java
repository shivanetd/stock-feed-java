package com.shiva.stockfeed.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shiva.stockfeed.model.*;

public interface StockFeedSnapshotRepository extends MongoRepository<StockMessage, String> {

    
    
}