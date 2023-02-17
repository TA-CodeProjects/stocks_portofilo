package com.ta.stock_protfolio.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ta.stock_protfolio.beans.Stock;
import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.exceptions.SystemException;
import com.ta.stock_protfolio.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("stock")
    @ResponseStatus(HttpStatus.CREATED)
    public Stock addStock(@RequestBody Stock stock) throws SystemException, JsonProcessingException {
        return adminService.addStock(stock);
    }

    @DeleteMapping("stock/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStock(@PathVariable long id) throws SystemException {
        adminService.deleteStock(id);
    }

    @GetMapping("stock")
    public List<Stock> getAllStock(){
        return adminService.getAllStocks();
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody @Valid User user) throws SystemException {
        return adminService.addUser(user);
    }

    @PutMapping("user/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) throws SystemException {
       return adminService.updateUser(user);
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) throws SystemException {
        adminService.deleteUser(id);
    }

    @GetMapping("user")
    public List<User> getAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("user/{id}")
    public User getOneUser(@PathVariable long id) throws SystemException {
        return adminService.getOneUser(id);
    }

}
