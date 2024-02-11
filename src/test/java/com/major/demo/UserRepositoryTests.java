/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    
    @Autowired
    private UserRepository repo;
    
    
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void testCreateUser(){
//        User user = new User();
//        user.setEmail("john@gmail.com");
//        user.setPassword("john20");
//        user.setFirstName("John");
//        user.setLastName("Steth");
//        
//        
//        
//        User savedUser = repo.save(user);
//        
//        User existUser = entityManager.find(User.class, savedUser.getId());
//        
//        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }
    
    
    
    @Test
    public void testFindUserByEmail(){
//        String email = "joe@gmail.com";
//        User user = repo.findByEmail(email);
//        assertThat(user).isNotNull();
    }
    
}
