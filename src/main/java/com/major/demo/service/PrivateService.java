/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.major.demo.service;

import com.major.demo.privatepost.Private;
import java.util.List;

/**
 *
 * @author DHANSHRI
 */
public interface PrivateService {
    
    List<Private> getAllData();
    
    void savePrivateSnippet(Private pri);
   
   Private get(int id);
   
   void save(Private pri);
   
   void delete(int id);
   
    public void delete();
    
    List<Private> getUserPrivateSnippets(Long userId); 
    
    void deleteSnippet(Long snippetId, Long userId);
    
}
