package com.ta.stock_protfolio.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ta.stock_protfolio.beans.Stock;
import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.exceptions.ErrorMessage;
import com.ta.stock_protfolio.exceptions.SystemException;
import com.ta.stock_protfolio.repos.StockRepository;
import com.ta.stock_protfolio.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final StockService stockService;

    @Override
    public Stock addStock(Stock stock) throws SystemException, JsonProcessingException {
        if (stockRepository.existsByStockName(stock.getStockName())){
            throw new SystemException(ErrorMessage.STOCK_EXISTS);
        }
        stockService.getStockDataFromApi(stock.getStockName());
        return stockRepository.save(stock);
    }

    @Override
    public void deleteStock(long stockId) throws SystemException {
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new SystemException(ErrorMessage.STOCK_NOT_EXISTS));
        stockService.deleteStockData(stock.getStockName());
        stockRepository.deleteById(stockId);
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public User addUser(User user) throws SystemException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new SystemException(ErrorMessage.EMAIL_ALREADY_EXISTS);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) throws SystemException {
        User userToUpdate = userRepository.findById(user.getId()).orElseThrow(() -> new SystemException(ErrorMessage.ID_NOT_EXISTS));
        user.setStocks(userToUpdate.getStocks());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(long userId) throws SystemException {
        if (!userRepository.existsById(userId)){
            throw new SystemException(ErrorMessage.ID_NOT_EXISTS);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getOneUser(long userId) throws SystemException {
        return userRepository.findById(userId).orElseThrow(() -> new SystemException(ErrorMessage.ID_NOT_EXISTS));
    }
}
