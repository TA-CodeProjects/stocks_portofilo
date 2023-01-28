package com.ta.stock_protfolio.controller;

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

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody @Valid User user) throws SystemException {
        return adminService.addUser(user);
    }

    @PutMapping("user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable long id, @RequestBody User user) throws SystemException {
        adminService.updateUser(user);
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
