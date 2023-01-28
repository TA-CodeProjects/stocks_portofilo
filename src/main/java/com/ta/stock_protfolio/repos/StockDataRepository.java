package com.ta.stock_protfolio.repos;

import com.ta.stock_protfolio.beans.StockData;
import com.ta.stock_protfolio.beans.StockPerformance;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StockDataRepository extends MongoRepository<StockData, String> {
    @Aggregation({"{'$project': { 'days': {'$arrayElemAt': ['$days', 0]}, 'days7': {'$arrayElemAt': ['$days', 5]}," +
            " 'days30': {'$arrayElemAt': ['$days', 22]}, 'days90': {'$arrayElemAt': ['$days', 66]}}}"})
    List<StockPerformance> getStockPerformance();

}
