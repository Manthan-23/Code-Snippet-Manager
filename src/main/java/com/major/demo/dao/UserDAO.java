/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.major.demo.dao;

import com.major.demo.User;
import java.util.List;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDAO {
    
   
    
    
   List<User> get();
   
   User get(int id);
   
   void save(User user);
   
   void delete(int id);

    
}
