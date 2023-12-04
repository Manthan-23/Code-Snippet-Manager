/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.dao;

import com.major.demo.privatepost.Private;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DHANSHRI
 */

@Repository
public class PrivateDAOImpl implements PrivateDAO {
    
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private JdbcTemplate jdbcTemplateTwo;

    @Override
    public List<Private> get() {
        
        return null;
        
    }

    @Override
    public Private get(int id) {
        
        return null;
        
    }

    @Override
    public void save(Private pri) {
        
    }

    @Override
    public void delete(int id) {
        
    }
    
}
