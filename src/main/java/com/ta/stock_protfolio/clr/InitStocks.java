package com.ta.stock_protfolio.clr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.stock_protfolio.beans.Stock;
import com.ta.stock_protfolio.repos.StockRepository;
import com.ta.stock_protfolio.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@Order(1)
@RequiredArgsConstructor
public class InitStocks implements CommandLineRunner {
    private final StockService stockService;
    private final StockRepository stockRepository;
    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${stocks_url}")
    private String BASE_URL;
    @Value("${sma_url}")
    private String SMA_URL;
    @Value("${XRapidAPIKey}")
    private String XRapidAPIKey;
    @Value("${XRapidAPIHost}")
    private String XRapidAPIHost;

    @Override
    public void run(String... args) throws Exception {
        List<String> stocks = List.of("AAPL","MSFT","GOOG","AMZN","WMT","TSLA","BABA");

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

//           StockData stock = stockRepository.findByStockId("AAPL", 66);
//        System.out.println(stock);

//        stockService.getStockDataFromApi("AAPL");

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-RapidAPI-Key", XRapidAPIKey);
//        headers.add("X-RapidAPI-Host", XRapidAPIHost);
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> responseSma = restTemplate.exchange(SMA_URL + "AAPL", HttpMethod.GET, entity, String.class);
//
//
//        JsonNode nodeSma = objectMapper.readTree(responseSma.getBody());
//        Map<String, Map<String, Object>> resultSma = objectMapper.convertValue(nodeSma.get("Technical Analysis: SMA"), new TypeReference<>() {
//        });

//        List<Sma> smas = new ArrayList<>();
//        resultSma.keySet().forEach(key -> smas.add(new Sma(LocalDate.parse(key), Double.parseDouble(resultSma.get(key).get("SMA").toString())) ));
    }
}
