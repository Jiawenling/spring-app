package com.example.demo.Model;

import java.util.List;

public class JwtResponse {
    private String token;
    private String username;
    private String name;
    private List<String> roles;
  
    public JwtResponse(String accessToken, String username, String name, List<String> roles) {
      this.token = accessToken;
      this.username = username;
      this.name = name;
      this.roles = roles;
    }

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public List<String> getRoles() {
      return roles;
    }

    public void setRoles(List<String> roles) {
      this.roles = roles;
    }
  
}
