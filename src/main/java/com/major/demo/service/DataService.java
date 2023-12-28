/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.major.demo.service;

import com.major.demo.data.Data;
import java.util.List;


public interface DataService {
    
    List<Data> getAllData();
    
    void saveSnippet(Data data);
   
   Data get(int id);
   
   void save(Data data);
   
   void delete(int id);
   
    public void delete();
    
    List<Data> findByTitleContaining(String title);
    
}
