package com.ta.stock_protfolio.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ta.stock_protfolio.beans.Day;
import com.ta.stock_protfolio.beans.StockData;
import com.ta.stock_protfolio.beans.StockPerformance;
import com.ta.stock_protfolio.exceptions.ErrorMessage;
import com.ta.stock_protfolio.exceptions.SystemException;
import com.ta.stock_protfolio.repos.StockDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockDataRepository stockDataRepository;
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
    public void getStockDataFromApi(String stockName) throws JsonProcessingException {
        JsonNode node = getJsonFormApi(BASE_URL, stockName);
        Map<String, Map<String, Object>> result = objectMapper.convertValue(node.get("Time Series (Daily)"), new TypeReference<>() {
        });

        JsonNode nodeSma = getJsonFormApi(SMA_URL, stockName);
        Map<String, Map<String, Object>> resultSma = objectMapper.convertValue(nodeSma.get("Technical Analysis: SMA"), new TypeReference<>() {
        });

        List<Day> days = new ArrayList<>();
        result.keySet().forEach(key -> days.add(new Day(LocalDate.parse(key), Double.parseDouble(result.get(key).get("4. close").toString()),
                Double.parseDouble(resultSma.get(key).get("SMA").toString()))));


        stockDataRepository.save(new StockData(
                node.get("Meta Data").get("2. Symbol").asText(),
                LocalDate.parse(node.get("Meta Data").get("3. Last Refreshed").asText()),
                days
        ));
    }

    private JsonNode getJsonFormApi(String url, String stockName) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", XRapidAPIKey);
        headers.add("X-RapidAPI-Host", XRapidAPIHost);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url + stockName, HttpMethod.GET, entity, String.class);
        return objectMapper.readTree(response.getBody());
    }

    @Override
    public void deleteStockData(String stockName) throws SystemException {
        if (!stockDataRepository.existsById(stockName)) {
            throw new SystemException(ErrorMessage.STOCK_NOT_EXISTS);
        }
        stockDataRepository.deleteById(stockName);
    }

    @Override
    public List<StockPerformance> getStockPerformance() {
        return stockDataRepository.getStockPerformance();
    }

    @Override
    @Cacheable("stocks")
    public StockData getStockDataFor66Days(String stockName) throws SystemException {
        if (!stockDataRepository.existsById(stockName)) {
            throw new SystemException(ErrorMessage.STOCK_NOT_EXISTS);
        }
        return stockDataRepository.findByStockId(stockName, 66);
    }
}
