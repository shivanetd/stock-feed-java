package com.shiva.stockfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import com.shiva.stockfeed.client.StockFeedProviderWSClient;
import com.shiva.stockfeed.service.StockFeedHandlerService;


@SpringBootApplication
public class StockFeedApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StockFeedApplication.class, args);

		StockFeedProviderWSClient client = context.getBean(StockFeedProviderWSClient.class);
		StockFeedHandlerService handler = context.getBean(StockFeedHandlerService.class);
		client.connect(handler);
	}

}
