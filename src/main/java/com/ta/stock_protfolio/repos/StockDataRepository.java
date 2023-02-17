package com.ta.stock_protfolio.repos;

import com.ta.stock_protfolio.beans.Day;
import com.ta.stock_protfolio.beans.StockData;
import com.ta.stock_protfolio.beans.StockPerformance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StockDataRepository extends MongoRepository<StockData, String> {
    @Aggregation({"{'$project': { 'day0': {'$arrayElemAt': ['$days', 0]}, 'day1': {'$arrayElemAt': ['$days', 1]}, 'day5': {'$arrayElemAt': ['$days', 5]}," +
            " 'day22': {'$arrayElemAt': ['$days', 22]}, 'day66': {'$arrayElemAt': ['$days', 66]}}}"})
    List<StockPerformance> getStockPerformance();

    @Query(value = "{'_id':?0}", fields = "{'days': {$slice: ?1}}")
    StockData findByStockId(String id, int slice);

    @Query("{'_id': ?0}")
    @Modifying
    @Transactional
    void updateStockPrices(String id, @Param("prices") List<Day> days);

}
