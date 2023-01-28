package com.ta.stock_protfolio.repos;

import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.beans.UserStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStockRepository extends JpaRepository<UserStock, Long> {
    UserStock findByUserAndStockName(User user, String stockName);

    List<UserStock> findByUser(User user);
}
