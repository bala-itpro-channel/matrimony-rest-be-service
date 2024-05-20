package com.javatalent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatalent.entity.AuthRequest;
import com.javatalent.entity.UserInfo;
import com.javatalent.service.JwtService;
import com.javatalent.service.UserInfoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth") 

public class UserController {
	
    @Autowired
    private UserInfoService service;
  
    @Autowired
    private JwtService jwtService; 
  
    @Autowired
    private AuthenticationManager authenticationManager; 
  
    // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addNewUser") 
    public UserInfo addNewUser(@RequestBody UserInfo userInfo) { 
    	UserInfo op = service.addUser(userInfo);
        return op; 
    }
  
    @PostMapping("/generateToken") 
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) { 
        Authentication authentication = authenticationManager
        		.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), 
        				authRequest.getPassword()));
        if (authentication.isAuthenticated()) { 
            return jwtService.generateToken(authRequest.getUsername()); 
        } else { 
            throw new UsernameNotFoundException("invalid user request !"); 
        } 
    } 
  
} 