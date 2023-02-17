package com.ta.stock_protfolio.repos;

import com.ta.stock_protfolio.beans.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    boolean existsByStockName(String stockName);
}
