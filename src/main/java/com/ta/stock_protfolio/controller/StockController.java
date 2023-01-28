package com.ta.stock_protfolio.controller;

import com.ta.stock_protfolio.beans.StockPerformance;
import com.ta.stock_protfolio.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping
    public List<StockPerformance> getStockPerformance(){
        return stockService.getStockPerformance();
    }
}
