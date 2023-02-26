package com.ta.stock_protfolio.jobs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ta.stock_protfolio.beans.Stock;
import com.ta.stock_protfolio.repos.StockRepository;
import com.ta.stock_protfolio.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class StocksUpdate {
    private final StockService stockService;
    private final StockRepository stockRepository;

    @Scheduled(cron = "${CRON_JOB}")
    public void run() throws JsonProcessingException, InterruptedException {
        List<Stock> stockList = stockRepository.findAll();

        int count = 1;
        for (Stock stock:stockList) {
            stockService.getStockDataFromApi(stock.getStockName());
            if (count++%2 == 0){
                TimeUnit.MINUTES.sleep(1);
            }
        }
    }

}
