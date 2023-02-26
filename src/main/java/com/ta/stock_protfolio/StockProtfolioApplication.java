package com.ta.stock_protfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories
@EnableCaching
//@EnableScheduling
public class StockProtfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockProtfolioApplication.class, args);
	}

}
