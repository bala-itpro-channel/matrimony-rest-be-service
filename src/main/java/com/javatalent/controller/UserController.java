package com.javatalent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javatalent.entity.AuthRequest;
import com.javatalent.entity.UserInfo;
import com.javatalent.service.JwtService;
import com.javatalent.service.UserInfoService;

import io.jsonwebtoken.io.IOException;

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
  
    @PostMapping("/addNewUserWithImage") 
    public UserInfo addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam("imageFile") MultipartFile imageFile) throws IOException { 
    	UserInfo op = null;
		try {
			op = service.addUserWithImage(userInfo, imageFile);
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return op;
    }
    
    @PostMapping("/addNewUser") 
    public UserInfo addNewUser(@RequestBody UserInfo userInfo) throws IOException { 
    	UserInfo op = null;
		try {
			op = service.addUser(userInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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