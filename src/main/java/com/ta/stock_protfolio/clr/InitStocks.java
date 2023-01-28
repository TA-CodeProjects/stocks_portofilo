package com.ta.stock_protfolio.clr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.stock_protfolio.beans.Day;
import com.ta.stock_protfolio.beans.StockData;
import com.ta.stock_protfolio.beans.StockPerformance;
import com.ta.stock_protfolio.repos.StockDataRepository;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class InitStocks implements CommandLineRunner {
    private final RestTemplate restTemplate;
    private final StockDataRepository stockRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String BASE_URL = "https://alpha-vantage.p.rapidapi.com/query?function=TIME_SERIES_DAILY&outputsize=compact&datatype=json&symbol=";
    @Value("${listOfStocks}")
    private List<String> stockList;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Data creation started...");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-RapidAPI-Key", "6bbf34725cmshaf6afcf8e0fa7b9p174c96jsn2cf192636088");
//        headers.add("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com");
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        int count = 1;
//        for (String stockName:stockList) {
//            ResponseEntity<String> response = restTemplate.exchange(BASE_URL + stockName, HttpMethod.GET, entity, String.class);
//
//            JsonNode node = objectMapper.readTree(response.getBody());
//
//            Map<String, Map<String, Object>> result;
//            result = objectMapper.convertValue(node.get("Time Series (Daily)"), new TypeReference<>() {
//            });
//
//            List<Day> days = new ArrayList<>();
//            result.keySet().forEach(k -> days.add(new Day(LocalDate.parse(k), Double.parseDouble(result.get(k).get("4. close").toString()))));
//
//            stockRepository.save(new StockData(
//                    node.get("Meta Data").get("2. Symbol").asText(),
//                    LocalDate.parse(node.get("Meta Data").get("3. Last Refreshed").asText()),
//                    days
//            ));
//
//            if (count++%5 == 0){
//                TimeUnit.MINUTES.sleep(2);
//            }
//        }
//
//
//
//        System.out.println("Data creation complete...");

//           List<StockPerformance> days =  stockRepository.getStockPerformance();
//        System.out.println(days);


    }
}
