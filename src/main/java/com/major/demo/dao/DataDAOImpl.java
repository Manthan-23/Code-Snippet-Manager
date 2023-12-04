/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.dao;

import com.major.demo.data.Data;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DataDAOImpl implements DataDAO {
    
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private JdbcTemplate jdbcTemplateTwo;
    

    @Override
    public List<Data> get() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Data> query = currentSession.createQuery("from Data", Data.class);
        List<Data> list = query.getResultList();
        return list;

    }

    @Override
    public Data get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Data dataObj = currentSession.get(Data.class, id);
        return dataObj;
    }

    @Override
    public void save(Data data) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(data);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Data dataObj = currentSession.get(Data.class ,id);
        currentSession.delete(dataObj);
    }
    
}
