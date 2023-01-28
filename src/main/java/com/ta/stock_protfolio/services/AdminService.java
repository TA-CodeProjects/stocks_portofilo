package com.ta.stock_protfolio.services;

import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.exceptions.SystemException;

import java.util.List;

public interface AdminService {
    User addUser(User user) throws SystemException;

    void updateUser(User user) throws SystemException;

    void deleteUser(long userId) throws SystemException;

    List<User> getAllUsers();

    User getOneUser(long userId) throws SystemException;
}
