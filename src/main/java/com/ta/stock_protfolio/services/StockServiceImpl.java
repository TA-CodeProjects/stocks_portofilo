package com.ta.stock_protfolio.services;

import com.ta.stock_protfolio.beans.StockPerformance;
import com.ta.stock_protfolio.repos.StockDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService{
    private final StockDataRepository stockDataRepository;

    @Override
    public List<StockPerformance> getStockPerformance() {
        return stockDataRepository.getStockPerformance();
    }
}
