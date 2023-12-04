/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.major.demo.dao;

import com.major.demo.privatepost.Private;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DHANSHRI
 */

@Repository
public interface PrivateDAO {
    
    List<Private> get();
   
   Private get(int id);
   
   void save(Private pri);
   
   void delete(int id);
    
}
