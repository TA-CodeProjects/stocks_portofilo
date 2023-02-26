package com.ta.stock_protfolio.services;

import com.ta.stock_protfolio.beans.TransactionType;
import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.beans.UserStock;
import com.ta.stock_protfolio.beans.TransactionHistory;
import com.ta.stock_protfolio.exceptions.SystemException;

import java.util.List;

public interface UserService {

    void setUser(User user);

    User findByEmail(String email) throws SystemException;

    UserStock makeTransaction(String stockName, int amount, double price, TransactionType transactionType, String date) throws SystemException;

    List<TransactionHistory> getTransactionHistory(long userStockId);

    List<UserStock> getAllStocks();

    UserStock getOneStock(long id) throws SystemException;

    void registerNewUserAccount(User user) throws SystemException;

}
