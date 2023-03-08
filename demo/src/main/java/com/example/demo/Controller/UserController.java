package com.example.demo.Controller;

import java.security.Principal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.hibernate.id.enhanced.LegacyHiLoAlgorithmOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.example.demo.JwtUtils;
import com.example.demo.Model.JwtResponse;
import com.example.demo.Model.LoginRequestModel;
import com.example.demo.Model.User;
import com.example.demo.Model.UserDetailImpl;
import com.example.demo.Model.UserProfile;
import com.example.demo.Repo.UserRepository;
import com.example.demo.Service.UserAlreadyExistException;
import com.example.demo.Service.UserDetailServiceImpl;
import com.example.demo.Service.UserService;

import jakarta.validation.Valid;


@RestController
public class UserController {

    Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
	AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/api/manager")
    public ResponseEntity<String> GetManagerPage(){
        return ResponseEntity.ok().body("Only a manager can view this");
    }

    // @GetMapping("/api/profile")
    // public ResponseEntity<UserProfile> GetProfilePage(Principal principal){
    //     boolean isManager  = authenticationFacade.getAuthentication().getAuthorities().stream().anyMatch(r-> r.getAuthority().equals("MANAGER"));
    //     User user= userRepository.findByUsername(principal.getName()).get();
    //     String managerLink;
    //     if (isManager) managerLink = "/manager";
    //     else managerLink="";
    //     UserProfile userProfile= new UserProfile(user.getName(), user.getUsername(), isManager? "MANAGER": "USER", managerLink);
    //     return new ResponseEntity<UserProfile>(userProfile, HttpStatusCode.valueOf(200));
    // }

    @PostMapping("/api/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestModel loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getUsername(), 
												 userDetails.getName(), 
												 roles));
	}
}
