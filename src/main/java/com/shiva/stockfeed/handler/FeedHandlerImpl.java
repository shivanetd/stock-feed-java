package com.shiva.stockfeed.handler;

import org.springframework.stereotype.Component;

import com.shiva.stockfeed.model.StockBarMessage;
import com.shiva.stockfeed.model.StockQuoteMessage;
import com.shiva.stockfeed.model.StockTradeMessage;
import com.shiva.stockfeed.repository.StockBarRepository;
import com.shiva.stockfeed.repository.StockQuoteRepository;
import com.shiva.stockfeed.repository.StockTradeRepository;

@Component
public class FeedHandlerImpl implements FeedHandler {

    private StockBarRepository barRepository;

    private StockQuoteRepository quoteRepository;

    private StockTradeRepository tradeRepository;


    public FeedHandlerImpl(StockBarRepository barRepository, StockQuoteRepository quoteRepository, StockTradeRepository tradeRepository){
        this.barRepository = barRepository;
        this.quoteRepository = quoteRepository;
        this.tradeRepository = tradeRepository;
    }

    @Override
    public void genericFeed(StockBarMessage message) {
        barRepository.insert(message);
    }

    @Override
    public void genericFeed(StockTradeMessage message) {
        tradeRepository.insert(message);
    }

    @Override
    public void genericFeed(StockQuoteMessage message) {
        quoteRepository.insert(message);
    }
    
}
