/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo;

import com.major.demo.data.Data;
import com.major.demo.datarepository.DataRepository;
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
public class DataRepositoryTests {
    
    @Autowired
    private DataRepository data;
    
    @Autowired
    private TestEntityManager entityManager;
    
    
    @Test
    public void testCreateData(){
//        Data dataa = new Data();
//    
//        dataa.setPost("Javascript");
//        
//        
//        
//        Data savedData = data.save(dataa);
//        
//        Data existData = entityManager.find(Data.class, savedData.getPost());
//        
//        assertThat(existData.getPost()).isEqualTo(dataa.getPost());
    }
    
}
