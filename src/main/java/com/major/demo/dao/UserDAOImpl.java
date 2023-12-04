/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.dao;

import com.major.demo.User;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private JdbcTemplate jdbcTemplateOne;
    
    @Override
    public List<User> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User", User.class);
        List<User> list = query.getResultList();
        return list;
    }

    @Override
    public User get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User userObj = currentSession.get(User.class, id);
        return userObj;
    }

    @Override
    public void save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User userObj = currentSession.get(User.class ,id);
        currentSession.delete(userObj);
    }

 
    
}
