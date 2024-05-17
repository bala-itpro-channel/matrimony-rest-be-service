package com.javatalent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javatalent.entity.UserInfo;
import com.javatalent.repo.UserInfoRepository;
import com.javatalent.service.UserInfoService;
import com.javatalent.exception.ResourceNotFoundException;
@RestController
@RequestMapping("/api")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
public class UserInfoController {
	@Autowired
    private UserInfoService service; 
	
	@Autowired
	UserInfoRepository repo;
	
	@GetMapping("/users")
	public List<UserInfo> getAllUsers() {
		return repo.findAll();
	}
	
	@GetMapping("/users/{id}")
	// @RequestMapping(path = "/mno/objectKey/{id}/{name}", method = RequestMethod.GET)
	public UserInfo getUserById(@PathVariable long id) {
		return service.getListById(id);
	}
	
	@GetMapping("/users/{pagesize}/{pagenumber}")
	// @RequestMapping(path = "/mno/objectKey/{id}/{name}", method = RequestMethod.GET)
	public List<UserInfo> getUserWithPagination(@PathVariable int pagesize, @PathVariable int pagenumber) {
		return service.getListWithPagination(pagesize, pagenumber);
	}	
	
	
}
