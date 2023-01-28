package com.ta.stock_protfolio.services;

import com.ta.stock_protfolio.beans.TransactionType;
import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.beans.UserStock;
import com.ta.stock_protfolio.beans.TransactionHistory;
import com.ta.stock_protfolio.exceptions.ErrorMessage;
import com.ta.stock_protfolio.exceptions.SystemException;
import com.ta.stock_protfolio.repos.UserRepository;
import com.ta.stock_protfolio.repos.TransactionHistoryRepository;
import com.ta.stock_protfolio.repos.UserStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserStockRepository userStockRepository;
    private final TransactionHistoryRepository userStockHistoryRepository;
    private User user;

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public User findByEmail(String email) throws SystemException {
        return userRepository.findByEmail(email).orElseThrow(() -> new SystemException(ErrorMessage.EMAIL_NOT_FOUND));
    }

    @Override
    public void makeTransaction(String stockName, int amount, double price, TransactionType transactionType, String date) throws SystemException {
        UserStock userStock = userStockRepository.findByUserAndStockName(user, stockName);
        if (userStock != null) {
            if (transactionType.equals(TransactionType.BUY)){
                userStock.setAmount(userStock.getAmount() + amount);
            } else {
                if (userStock.getAmount() < amount){
                    throw new SystemException(ErrorMessage.NOT_ENOUGH_STOCKS_TO_SELL);
                }
                userStock.setAmount(userStock.getAmount() - amount);
            }
        } else {
            userStock = UserStock.builder().user(user).stockName(stockName).amount(amount).build();
        }
        userStockRepository.save(userStock);
        TransactionHistory userStockHistory = TransactionHistory.builder().userStock(userStock).transactionType(transactionType).date(LocalDate.parse(date)).amount(amount).price(price).build();
        userStockHistoryRepository.save(userStockHistory);

    }

    @Override
    public List<UserStock> getAllStocks(){
       return userStockRepository.findByUser(user);
    }

    @Override
    public UserStock getOneStock(long id) throws SystemException {
       return userStockRepository.findById(id).orElseThrow(() -> new SystemException(ErrorMessage.ID_NOT_EXISTS));

    }




}
