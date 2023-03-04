package com.example.demo.Model;

public class UserProfile {
    private String Name;
    private String UserName;
    private String Role;
    private String PrivateLink;


    public UserProfile(String name, String userName, String role, String privateLink) {
        Name = name;
        UserName = userName;
        Role = role;
        PrivateLink = privateLink;
    }


    public String getName() {
        return Name;
    }


    public String getUserName() {
        return UserName;
    }


    public String getRole() {
        return Role;
    }


    public String getPrivateLink() {
        return PrivateLink;
    }

    
    
}
