/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.major.demo.service;

import com.major.demo.User;
import java.util.List;



public interface UserService {
   List<User> get();
   
   User get(int id);
   
   void save(User user);
   
   void delete(int id);
   
   public void delete();
   
   public boolean changePassword(String email, String currentPassword, String newPassword);
}
