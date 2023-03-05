package com.example.demo.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.AuthenticationFacade;
import com.example.demo.Model.LoginRequestModel;
import com.example.demo.Model.User;
import com.example.demo.Model.UserProfile;
import com.example.demo.Repo.UserRepository;
import com.example.demo.Service.UserAlreadyExistException;
import com.example.demo.Service.UserDetailServiceImpl;
import com.example.demo.Service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    AuthenticationFacade authenticationFacade;

    
    @GetMapping("/api/manager")
    public String GetManagerPage(){
        return "Only a manager can access this!";
    }


    @GetMapping("/api/profile")
    public ResponseEntity<UserProfile> GetProfilePage(Principal principal){
        boolean isManager  = authenticationFacade.getAuthentication().getAuthorities().stream().anyMatch(r-> r.getAuthority().equals("MANAGER"));
        User user= userRepository.findByUsername(principal.getName()).get();
        String managerLink;
        if (isManager) managerLink = "/manager";
        else managerLink="";
        UserProfile userProfile= new UserProfile(user.getName(), user.getUsername(), isManager? "MANAGER": "USER", managerLink);
        return new ResponseEntity<UserProfile>(userProfile, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/api/login")
    public ResponseEntity<Object> SignIn(@RequestBody LoginRequestModel loginRequest){
        try{
            userDetailService.loadUserByUsername(loginRequest.getUsername());
            return ResponseEntity.ok().build();
        }
        catch(UsernameNotFoundException ex){
            return ResponseEntity.badRequest().build();
        }
    }
    

    // @PostMapping("/signup")
    // public ResponseEntity<String> Signup(@Valid @RequestBody User user){
    //     try{
    //         userService.registerNewUserAccount(user);
    //         return ResponseEntity.ok().body("Successfully registered!");
    //     }catch(UserAlreadyExistException ex){
    //         return ResponseEntity.badRequest().body("User already exists!");
    //     }
    // }
}
