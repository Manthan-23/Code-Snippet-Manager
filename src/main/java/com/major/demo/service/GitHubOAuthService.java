/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.service;

import com.major.demo.GithubRepository;
import com.major.demo.User;
import com.major.demo.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author DHANSHRI
 */
@Service
public class GitHubOAuthService {
    
    @Autowired
    private UserRepository repo;
   
    @Autowired
    private GithubRepository githubrepo;
    
    private final RestTemplate restTemplate;
    private final String clientId;
    private final String clientSecret;

     public GitHubOAuthService(RestTemplate restTemplate,
                              @Value("${spring.security.oauth2.client.registration.github.client-id}") String clientId,
                              @Value("${spring.security.oauth2.client.registration.github.client-secret}") String clientSecret) {
        this.restTemplate = restTemplate;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
     
    public void storeTokensForUser(String email, String accessToken, String refreshToken) {
        User user = repo.findByEmail(email);
        if (user == null) {
            // Create a new user if not found
//            user = new User();
//            user.setUsername(username);
            System.out.println("User is Null!!");
        }

        // Update the access token and refresh token
        user.setAccess_token(accessToken);
        user.setRefresh_token(refreshToken);

        repo.save(user);
    }

    public GitHubAccessTokenResponse getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", "application/json");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<GitHubAccessTokenResponse> response = restTemplate.postForEntity(
                "https://github.com/login/oauth/access_token",
                request,
                GitHubAccessTokenResponse.class);

//        if (response.getStatusCode() == HttpStatus.OK) {
//            GitHubAccessTokenResponse responseBody = response.getBody();
//            if (responseBody != null) {
//                return responseBody.getAccessToken();
//            }
//        }

          if (response.getStatusCode() == HttpStatus.OK) {
            GitHubAccessTokenResponse responseBody = response.getBody();
            System.out.println("Access Token and Refresh Token is: " + responseBody);
            if(responseBody != null){
                return responseBody;

            }
        }
          return null;
    }
    
    public List<GithubRepository> getUserRepositories(String username, String accessToken) {
        String apiUrl = "https://api.github.com/users/" + username + "/repos";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<GithubRepository[]> response = restTemplate.exchange(
            apiUrl, HttpMethod.GET, entity, GithubRepository[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
            
        } else {
            throw new RuntimeException("Failed to fetch repositories from GitHub API.");
        }
    }
    
    public String refreshAccessToken(String refreshToken) {
    // Set up the HTTP headers
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    // Set up the request body with refresh token and client ID/secret
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("grant_type", "refresh_token");
    body.add("refresh_token", refreshToken);
    body.add("client_id", clientId);
    body.add("client_secret", clientSecret);

    // Create the HTTP entity with headers and body
    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

    // Make a POST request to GitHub API to refresh access token
    ResponseEntity<GitHubAccessTokenResponse> response = restTemplate.postForEntity(
            "https://github.com/login/oauth/access_token",
            request,
            GitHubAccessTokenResponse.class);

    if (response.getStatusCode() == HttpStatus.OK) {
        GitHubAccessTokenResponse tokenResponse = response.getBody();
        return tokenResponse.getAccessToken();
    } else {
        throw new RuntimeException("Failed to refresh access token.");
    }
}
    
}
