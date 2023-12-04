/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.major.demo.dao;

import com.major.demo.data.Data;
import java.util.List;
import org.springframework.stereotype.Repository;


@Repository
public interface DataDAO {
    
    
   List<Data> get();
   
   Data get(int id);
   
   void save(Data data);
   
   void delete(int id);
    
    
}
