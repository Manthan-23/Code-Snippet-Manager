/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoder {
    
    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "12345";
        String encodedPassword = encoder.encode(rawPassword);
        
        System.out.println(encodedPassword);
    }
    
}
