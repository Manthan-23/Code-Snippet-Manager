/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.major.demo.privaterepository;

import com.major.demo.User;
import com.major.demo.privatepost.Private;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DHANSHRI
 */

@Repository
public interface PrivateRepository extends JpaRepository<Private, Long> {
    
    List<Private> findByUser(User user);
    
}
