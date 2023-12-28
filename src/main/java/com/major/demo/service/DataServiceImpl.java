/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.service;

import com.major.demo.dao.DataDAO;
import com.major.demo.data.Data;
import com.major.demo.datarepository.DataRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DataServiceImpl implements DataService {
    
    @Autowired
    private DataDAO dataDAO;
    
    @Autowired
    private DataRepository data;

    @Transactional
    public List<Data> get() {
        return dataDAO.get();
    }
    
    
    @Override
    public List<Data> getAllData(){
        return data.findAll();
    }
    
    @Transactional
    @Override
    public Data get(int id) {
        return dataDAO.get(id);
    }

    @Transactional
    @Override
    public void save(Data data) {
        dataDAO.save(data);
    }

    @Transactional
    @Override
    public void delete(int id) {
        dataDAO.delete(id);
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    @Override
    public void saveSnippet(Data data) {
        this.data.save(data);
    }
    
    @Override
    public List<Data> findByTitleContaining(String title) {
    return data.findByTitleContaining(title);
}
    
    
    
}
