/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.major.demo.privatepost.Private;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import jdk.jshell.Snippet;




@Entity
@Table(schema = "snippetdb", name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    @Email(message = "Please provide a valid email address")
//    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    
//    @Size(min = 6, message = "Password must be at least 6 characters long")
//    @NotBlank(message = "Password is required")
    @Column(nullable = false, length = 64)
    private String password;
    
    @Column(nullable = false, length = 20)
    private String firstName;
    
    @Column(nullable = false, length = 20)
    private String lastName;
    
    @Column(nullable = false, length = 20)
    private String otp;
    
    
    @Column(nullable = false, length = 64)
    private Date expiry_time;
    
    @OneToMany(mappedBy = "user")
    private List<Private> privates;
    
    

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(Date expiry_time) {
        this.expiry_time = expiry_time;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
