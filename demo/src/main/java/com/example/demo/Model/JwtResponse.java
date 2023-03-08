package com.example.demo.Model;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String name;
    private List<String> roles;
  
    public JwtResponse(String accessToken, String username, String name, List<String> roles) {
      this.token = accessToken;
      this.username = username;
      this.name = name;
      this.roles = roles;
    }
  
    public String getAccessToken() {
      return token;
    }
  
    public void setAccessToken(String accessToken) {
      this.token = accessToken;
    }
  
    public String getTokenType() {
      return type;
    }
  
    public void setTokenType(String tokenType) {
      this.type = tokenType;
    }
  
    public String getName() {
      return name;
    }
  
    public void setName(String name) {
      this.name = name;
    }
  
    public String getUsername() {
      return username;
    }
  
    public void setUsername(String username) {
      this.username = username;
    }
  
    public List<String> getRoles() {
      return roles;
    }
}