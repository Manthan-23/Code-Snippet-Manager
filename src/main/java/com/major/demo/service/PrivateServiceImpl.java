/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.service;

import com.major.demo.User;
import com.major.demo.dao.PrivateDAO;
import com.major.demo.privatepost.Private;
import com.major.demo.privaterepository.PrivateRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DHANSHRI
 */

@Service
public class PrivateServiceImpl implements PrivateService {
    
    @Autowired
    private PrivateDAO priDAO;
    
    @Autowired
    private PrivateRepository pri;
    
    public List<Private> getSnippetsByUser(User user) {
        return pri.findByUser(user);
    }

    @Transactional
    public List<Private> get() {
        return priDAO.get();
    }
    
    
    @Override
    public List<Private> getAllData(){
        return pri.findAll();
    }
    
    @Transactional
    @Override
    public Private get(int id) {
        return priDAO.get(id);
    }

    @Transactional
    @Override
    public void save(Private pri) {
        priDAO.save(pri);
    }

    @Transactional
    @Override
    public void delete(int id) {
        priDAO.delete(id);
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public void savePrivateSnippet(Private pri) {
        this.pri.save(pri);
    }
    
    public List<Private> getUserPrivateSnippets(Long userId) {
        return pri.findByUserId(userId);
    }

    public List<Private> getUserPrivateSnippets() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    @Transactional
    public void deleteSnippet(Long snippetId, Long userId) {
        pri.deleteSnippet(snippetId, userId);
    }
    
    
    @Transactional
    @Override
    public Private getSnippetById(Long snippetId) {
        return pri.findById(snippetId)
                .orElseThrow(() -> new RuntimeException("Snippet not found with id: " + snippetId));
    }
    
    @Override
    public Private editPrivate(Long privateId, Private editedPrivate) throws NotFoundException {
        Optional<Private> existingPrivateOptional = pri.findById(privateId);

        if (existingPrivateOptional.isPresent()) {
            Private existingPrivate = existingPrivateOptional.get();

            // Update fields based on the editedPrivate
            existingPrivate.setTitle(editedPrivate.getTitle());
            existingPrivate.setPost(editedPrivate.getPost());

            // Save the updated private entity
            return pri.save(existingPrivate);
        } else {
            // Handle the case where the private entity is not found
            throw new NotFoundException("Private entity not found with ID: " + privateId);
        }
    }
}
