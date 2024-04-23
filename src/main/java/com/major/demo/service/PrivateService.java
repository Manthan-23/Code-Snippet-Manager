/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.major.demo.service;

import com.major.demo.privatepost.Private;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;

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
    
    public Optional<Private> findByUuid(String uuid);
    
    
    void deleteSnippet(Long snippetId, Long userId);
    
    Private getSnippetById(String snippetId);
    
    
    public interface PriServ {
    Private getSnippetById(Long snippetId);
}
    
}
