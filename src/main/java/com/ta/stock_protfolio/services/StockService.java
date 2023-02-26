package com.ta.stock_protfolio.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ta.stock_protfolio.beans.StockData;
import com.ta.stock_protfolio.beans.StockPerformance;
import com.ta.stock_protfolio.exceptions.SystemException;

import java.util.List;

public interface StockService {
    void getStockDataFromApi(String stockName) throws JsonProcessingException;

    void deleteStockData(String stockName) throws SystemException;

    List<StockPerformance> getStockPerformance();

    StockData getStockDataFor66Days(String stockName) throws SystemException;
}
