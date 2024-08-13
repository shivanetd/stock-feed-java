package com.shiva.stockfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import com.shiva.stockfeed.client.StockFeedProviderWSClient;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class StockFeedApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StockFeedApplication.class, args);

		StockFeedProviderWSClient client = context.getBean(StockFeedProviderWSClient.class);
		client.connect();
	}

}
