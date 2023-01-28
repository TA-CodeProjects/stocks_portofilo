package com.ta.stock_protfolio.controller;

import com.ta.stock_protfolio.beans.MakeTransaction;
import com.ta.stock_protfolio.beans.TransactionHistory;
import com.ta.stock_protfolio.beans.UserStock;
import com.ta.stock_protfolio.exceptions.SystemException;
import com.ta.stock_protfolio.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public String makeTransaction(@RequestBody MakeTransaction makeTransaction) throws SystemException {
        userService.makeTransaction(makeTransaction.getStockName(), makeTransaction.getAmount(), makeTransaction.getPrice()
                , makeTransaction.getTransactionType(),makeTransaction.getDate());
        return "Made transaction successfully";
    }

    @GetMapping
    public List<UserStock> getAllStocks() {
        return userService.getAllStocks();
    }

    @GetMapping("/{id}")
    public UserStock getOneStock(@PathVariable long id) throws SystemException {
        return userService.getOneStock(id);
    }


}
