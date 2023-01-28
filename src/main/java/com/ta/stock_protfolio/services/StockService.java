package com.ta.stock_protfolio.services;

import com.ta.stock_protfolio.beans.StockPerformance;

import java.util.List;

public interface StockService {
    List<StockPerformance> getStockPerformance();
}
