/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.major.demo.privaterepository;

import com.major.demo.User;
import com.major.demo.privatepost.Private;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DHANSHRI
 */

@Repository
public interface PrivateRepository extends JpaRepository<Private, Long> {
    
    Optional<Private> findByUuid(String uuid);
    
    List<Private> findByUser(User user);
     List<Private> findByUserId(Long userId);
     
      Optional<Private> findById(String uuid);
     
     void deleteByIdAndUserId(Long id, Long userId);
     
     @Modifying
     @Transactional
    @Query("DELETE FROM Private p WHERE p.id = :snippetId AND p.user.id = :userId")
    void deleteSnippet(@Param("snippetId") Long snippetId, @Param("userId") Long userId);
    
}
