package com.javatalent.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javatalent.entity.UserInfo;
import com.javatalent.repo.UserInfoRepository;
import com.javatalent.service.UserInfoService;

import io.jsonwebtoken.io.IOException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
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
	public UserInfo getUserById(@PathVariable long id) {
		return service.getListById(id);
	}
	
	@GetMapping("/users/{pagesize}/{pagenumber}")
	public List<UserInfo> getUserWithPagination(@PathVariable int pagesize, @PathVariable int pagenumber) {
		return service.getListWithPagination(pagesize, pagenumber);
	}
	
	@GetMapping("/userslist/{name}/{gender}")
	public List<UserInfo> getListByFirstNameAndGender(@PathVariable String name, @PathVariable String gender) {
		return service.getListByFirstNameAndGender(name, gender);
	}

	@GetMapping("/users/{pagesize}/{pagenumber}/{sortField}")
	public Page<UserInfo> getUserWithPaginationAndSorting(@PathVariable int pagesize, @PathVariable int pagenumber, @PathVariable String sortField) {
		return service.getProfileWithPaginationAndSorting(pagesize, pagenumber, sortField);
	}

//    @PostMapping("/upload/{id}") 
//    public UserInfo upload(@PathVariable int id,  @RequestParam("imageFile") MultipartFile imageFile) { 
//    	UserInfo op = null;
//		try {
//			op = service.uploadImage(id, imageFile);
//		} catch (java.io.IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return op;
//    }
    
//    // Option 2 to upload Image
//    @PostMapping("/uploadnew") 
//    public UserInfo uploadNew(@RequestParam("id") int id,  @RequestParam("imageFile") MultipartFile imageFile) { 
//    	UserInfo op = null;
//    	System.out.println("***************************************");
//		try {
//			op = service.uploadImage(id, imageFile);
//		} catch (java.io.IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return op;
//    }
    
//    @GetMapping(value = "/image/{id}")
//    public ResponseEntity<byte[]> getUserImage(@PathVariable int id) { 
//        byte[] imageBytes = null;
//		try {
//			imageBytes = service.getImage(id);
//		} catch (java.io.IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        
//        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK); 
//    }

}
