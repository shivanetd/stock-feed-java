package com.shiva.stockfeed.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import com.shiva.stockfeed.model.StockBarMessage;

public interface StockBarRepository extends MongoRepository<StockBarMessage, String> {

    List<StockBarMessage> findBySymbol(String symbol);
}