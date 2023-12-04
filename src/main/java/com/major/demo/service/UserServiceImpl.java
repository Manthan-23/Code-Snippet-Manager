/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.service;

import com.major.demo.User;
import com.major.demo.UserRepository;
import com.major.demo.dao.UserDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private UserRepository userRepository;
    
    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
   
    
    @Transactional
    @Override
    public List<User> get() {
        return userDAO.get();
    }

    @Transactional
    @Override
    public User get(int id) {
        return userDAO.get(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean changePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(currentPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    

    
    
}
