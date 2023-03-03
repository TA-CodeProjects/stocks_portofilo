package com.ta.stock_protfolio.clr;

import com.ta.stock_protfolio.beans.Stock;
import com.ta.stock_protfolio.repos.StockRepository;
import com.ta.stock_protfolio.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Order(1)
@RequiredArgsConstructor
public class InitStocks implements CommandLineRunner {
    private final StockService stockService;
    private final StockRepository stockRepository;



    @Override
    public void run(String... args) throws Exception {
        List<String> stocks = List.of("AAPL","MSFT","GOOG","AMZN","WMT","TSLA","BABA", "COST", "CSCO", "TMUS", "ADBE", "NFLX", "INTC", "PYPL");

        stocks.forEach(stock -> stockRepository.save(new Stock(stock)));

//        System.out.println("Data creation started...");
//
//        List<Stock> stockList = stockRepository.findAll();
//
//        int count = 1;
//        for (Stock stock:stockList) {
//           stockService.getStockDataFromApi(stock.getStockName());
//            if (count++%2 == 0){
//                TimeUnit.MINUTES.sleep(1);
//            }
//        }
//
//
//
//        System.out.println("Data creation complete...");

    }
}
