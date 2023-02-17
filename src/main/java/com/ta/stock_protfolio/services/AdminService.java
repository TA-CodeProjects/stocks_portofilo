package com.ta.stock_protfolio.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ta.stock_protfolio.beans.Stock;
import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.exceptions.SystemException;

import java.util.List;

public interface AdminService {
    Stock addStock(Stock stock) throws SystemException, JsonProcessingException;

    void deleteStock(long stockId) throws SystemException;

    List<Stock> getAllStocks();

    User addUser(User user) throws SystemException;

    User updateUser(User user) throws SystemException;

    void deleteUser(long userId) throws SystemException;

    List<User> getAllUsers();

    User getOneUser(long userId) throws SystemException;
}
