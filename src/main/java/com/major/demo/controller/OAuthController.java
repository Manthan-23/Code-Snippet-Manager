/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.controller;

import com.major.demo.GithubRepository;
import com.major.demo.User;
import com.major.demo.UserRepository;
import com.major.demo.privatepost.Private;
import com.major.demo.service.GitHubAccessTokenResponse;
import com.major.demo.service.GitHubOAuthService;
import com.major.demo.service.PrivateService;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * @author DHANSHRI
 */
@Controller
@SessionAttributes("accessToken")
public class OAuthController {
    
    @Autowired
    private GitHubOAuthService github;
    
    @Autowired
    private HttpSession session;
    
    @Autowired
    private UserRepository repo;
    
    @Autowired
    private PrivateService priService;
    
    @GetMapping("/refresh-token-page")
    public String refreshTokenPage() {
        return "refresh-token-page";
    }
    
    @GetMapping("/test-401")
public ResponseEntity<String> test401() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Test 401 Unauthorized");
}
    
     @GetMapping("/github/callback")
    public String handleGitHubCallback(@RequestParam("code") String code, Model model) {
        
        
        String email = (String) session.getAttribute("loginEmail");
        
//        String code1 = code;
        
        GitHubAccessTokenResponse tokenResponse = github.getAccessToken(code);
        System.out.println("TokenResponse = " + tokenResponse);
               

        // Handle the authorization code received from GitHub
        System.out.println("Authorization Code: " + code);
        
        System.out.println("Access Token and Refresh Token is: " + tokenResponse);
        
        if (tokenResponse != null) {
            github.storeTokensForUser(email, tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());
            String accessTokenNew = tokenResponse.getAccessToken();
            int expiresIn = tokenResponse.getExpires_in();
            System.out.println("expries in = " + expiresIn);
            Instant now = Instant.now();
            Instant expiryTime = now.plusSeconds(expiresIn); 
            System.out.println("AccessTokenNew = " + accessTokenNew);
            session.setAttribute("accessToken", accessTokenNew);
        }
        // You can now exchange this code for an access token
        // and proceed with the OAuth2 flow
        return "redirect:/github-login"; // Redirect to dashboard or desired page
    }
    
    public boolean isTokenExpired(Instant expiryTime) {
    Instant now = Instant.now();
    return now.isAfter(expiryTime);
}
    
    
    @GetMapping("/github-login")
    public String github(Model model){
        
        String email = (String) session.getAttribute("loginEmail");
        
        User userNew = repo.findByEmail(email);
        
        String accessToken = (String) session.getAttribute("accessToken");
        System.out.println("Access Token: " + accessToken);
        if (userNew != null && userNew.getAccess_token() != null && !userNew.getAccess_token().isEmpty()) {
                           
           
            System.out.println("User = " + userNew.getEmail());
            System.out.println("User = " + userNew.getAccess_token());
    
            String username = userNew.getFirstName();
            
             System.out.println("User = " + userNew.getFirstName());
            // User is already authorized, get username from session or database

            // Pass username to the view
            model.addAttribute("username", userNew.getUsername());
            
            String username2 = userNew.getUsername();
            
            List<GithubRepository> repositories = github.getUserRepositories(username2, userNew.getAccess_token());
        System.out.println("Repos = " + repositories);
        model.addAttribute("repositories", repositories);
            
            return "github-login";
        } 
        
        else {
         return "github-login";   
        }
        }
    
    
    
    @GetMapping("/github-repo")
    public String getGitHubRepositories(@RequestHeader("Authorization") String accessToken, Model model) {
        String email = (String) session.getAttribute("loginEmail");
        User userNew2 = repo.findByEmail(email);
        String username = userNew2.getUsername();
        List<GithubRepository> repositories = github.getUserRepositories(username, accessToken);
        System.out.println("Repos = " + repositories);
        model.addAttribute("repositories", repositories);
        return "github-login";
    }
    
    
    @PostMapping("/saveGithubPrivateSnippet")
    public String savePrivateSnippet(@ModelAttribute("pri") Private pri, @RequestParam("post") String post, HttpSession session, RedirectAttributes redirectAttributes) {
    
    String email = (String) session.getAttribute("loginEmail");
        
    // Get the email from the Private entity
//    String userEmail = pri.getEmail();

    // Retrieve the user from the database based on the email
    User user = repo.findByEmail(email);

    // Check if the user exists
    if (user != null) {
        // Get the user ID
        Long userId = user.getId();

        // Set the user_id in the Private entity
        pri.setUserId(userId);
        
         pri.setPost(post);
         
         pri.setCategory("Github");
        
        System.out.println("Title: " + pri.getTitle());
        System.out.println("Post Content before saving: " + pri.getPost());

        // Save the private snippet
        priService.savePrivateSnippet(pri);
        System.out.println("Post Content after saving: " + pri.getPost());

        return "redirect:/privatesnippets";
    } else {
        // Handle the case where the user with the specified email is not found
        // You might want to redirect to an error page or display an error message
        return "login"; // Adjust this based on your application's error handling mechanism
    }
}
    
}

