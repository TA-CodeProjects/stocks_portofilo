package com.ta.stock_protfolio.clr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.stock_protfolio.beans.Day;
import com.ta.stock_protfolio.beans.Stock;
import com.ta.stock_protfolio.beans.StockData;
import com.ta.stock_protfolio.exceptions.SystemException;
import com.ta.stock_protfolio.repos.StockDataRepository;
import com.ta.stock_protfolio.repos.StockRepository;
import com.ta.stock_protfolio.services.AdminService;
import com.ta.stock_protfolio.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Order(1)
@RequiredArgsConstructor
public class InitStocks implements CommandLineRunner {
    private final StockService stockService;
    private final StockRepository stockRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${stocks_url}")
    private String BASE_URL;
    @Value("${XRapidAPIKey}")
    private String XRapidAPIKey;
    @Value("${XRapidAPIHost}")
    private String XRapidAPIHost;

    @Override
    public void run(String... args) throws Exception {
        List<String> stocks = List.of("AAPL","MSFT","GOOG","AMZN","WMT","TSLA","BABA");

        stocks.forEach(stock -> stockRepository.save(new Stock(stock)));

        List<Stock> stockList = stockRepository.findAll();

        System.out.println("Data creation started...");

        int count = 1;
        for (Stock stock:stockList) {
           stockService.getStockDataFromApi(stock.getStockName());
            if (count++%5 == 0){
                TimeUnit.MINUTES.sleep(2);
            }
        }



        System.out.println("Data creation complete...");

//           StockData stock = stockRepository.findByStockId("AAPL", 66);
//        System.out.println(stock);


    }
}
