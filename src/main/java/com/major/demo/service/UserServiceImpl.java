/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.service;

import com.major.demo.User;
import com.major.demo.UserRepository;
import com.major.demo.dao.UserDAO;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.JavaMailSender;



@Service
public class UserServiceImpl implements UserService {
    
    private final JavaMailSender javaMailSender;
    
    @Autowired
    public UserServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private UserRepository userRepository;
    
    
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
   
    
    @Transactional
    @Override
    public List<User> get() {
        return userDAO.get();
    }

    @Transactional
    @Override
    public User get(int id) {
        return userDAO.get(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void saveOtpForUser(String email, String otp) {
        // Generate OTP
        otp = generateOtp();
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Your OTP for Verification");
        mailMessage.setText("Your OTP is: " + otp);

        javaMailSender.send(mailMessage);

        // Set expiration time (e.g., 10 minutes from now)
        Date expirationTime = new Date(System.currentTimeMillis() + 10 * 60 * 1000);

        // Save OTP and expiration time to the database for the user with the given email
        User user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("User not found")); // Handle appropriately
        user.setOtp(otp);
        user.setExpiry_time(expirationTime);
        userRepository.save(user);
    }
    
    @Override
    public String generateOtp() {
        // Generate a 6-digit random OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
    
    public boolean verifyOtp(String email, String otp) {
        // Retrieve user by email
        System.out.println("Email Now = " + email);
        User user = userRepository.findByEmail(email);
//                .orElseThrow(() -> new RuntimeException("User not found")); // Handle appropriately

        // Check if OTP is expired
//        if (user.getExpiry_time() != null && user.getExpiry_time().before(new Date())) {
//            return false; // OTP has expired
//        }
        System.out.println("Retrieved User: " + user);
        System.out.println("Entered OTP: " + otp);
        if(user != null){
        System.out.println("Stored OTP: " + user.getOtp());
        }
        // Compare entered OTP with stored OTP
        return otp.equals(user.getOtp());
    }
    
    @Override
    public boolean changePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(currentPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    public void saveOtpForUser(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    
    
}
