package com.ta.stock_protfolio.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MakeTransaction {
    private String stockName;
    private int amount;
    private double price;
    private TransactionType transactionType;
    private String date;
}
