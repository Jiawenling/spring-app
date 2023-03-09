package com.example.demo.Controller;

import java.util.List;

import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.JwtUtils;
import com.example.demo.Model.JwtResponse;
import com.example.demo.Model.LoginRequestModel;
import com.example.demo.Model.UserDetailImpl;
import com.example.demo.Repo.UserRepository;
import com.example.demo.Service.UserDetailServiceImpl;

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
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<String> GetManagerPage(){
        return ResponseEntity.ok().body("Only a manager can view this");
    }


    @PostMapping("/api/login")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequestModel loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
        JwtResponse response = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getName(), roles);
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwt).body(response);
	}
}
