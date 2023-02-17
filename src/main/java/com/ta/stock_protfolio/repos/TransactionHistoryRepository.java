package com.ta.stock_protfolio.repos;

import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.beans.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    List<TransactionHistory> findByUserStockId(long userStockId);
}
