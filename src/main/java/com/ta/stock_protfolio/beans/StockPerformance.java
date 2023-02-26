package com.ta.stock_protfolio.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class StockPerformance {
    @Id
    private String stockName;
    private Day day0;
    private Day day1;
    private Day day5;
    private Day day22;
    private Day day66;

}
