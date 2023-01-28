package com.ta.stock_protfolio.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Table(name = "stocks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonIgnore
    private User user;
    private int amount;
    private String stockName;
    @OneToMany(mappedBy = "userStock", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @ToString.Exclude
    @JsonIgnore
    private List<TransactionHistory> userStockHistoryList;
}
