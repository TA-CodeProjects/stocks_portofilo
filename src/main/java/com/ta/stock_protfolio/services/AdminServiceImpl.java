package com.ta.stock_protfolio.services;

import com.sun.xml.bind.v2.TODO;
import com.ta.stock_protfolio.beans.User;
import com.ta.stock_protfolio.exceptions.ErrorMessage;
import com.ta.stock_protfolio.exceptions.SystemException;
import com.ta.stock_protfolio.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;

    @Override
    public User addUser(User user) throws SystemException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new SystemException(ErrorMessage.EMAIL_ALREADY_EXISTS);
        }
        return userRepository.save(user);
    }

    @Override
    public void updateUser(User user) throws SystemException {
        // TODO - add validation
        userRepository.saveAndFlush(user);
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
