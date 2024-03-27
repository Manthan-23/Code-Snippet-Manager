/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.major.demo.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;

/**
 *
 * @author DHANSHRI
 */
public class GitHubAccessTokenResponse {
    
    @JsonProperty("access_token")
    private String accessToken;
    
    @JsonProperty("refresh_token")
    private String refreshToken;
    
    private int expires_in;
    
    public GitHubAccessTokenResponse() {
    }

    public GitHubAccessTokenResponse(String accessToken, String refreshToken, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expires_in = expires_in;
    }
  
    // Other properties if needed, such as token type, scope, etc.

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
    
    

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken(){
        return refreshToken;
    }
    
    public void setRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    
    
    
    
    @Override
    public String toString() {
        return "Access Token: " + accessToken + ", Refresh Token: " + refreshToken;
    }
}
