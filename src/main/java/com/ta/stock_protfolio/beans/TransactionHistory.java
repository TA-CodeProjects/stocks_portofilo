package com.ta.stock_protfolio.beans;

import javax.persistence.*;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_stock_history")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private UserStock userStock;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Min(value = 0L, message = "The value must be positive")
    private int amount;
    private LocalDate date;
    private double price;


}
