package com.javatalent.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javatalent.entity.UserInfo;
import com.javatalent.entity.UserInfoDetails;
import com.javatalent.repo.UserInfoRepository;
import com.javatalent.util.ImageUtils;

@Service
public class UserInfoService implements UserDetailsService { 
  
    @Autowired
    private UserInfoRepository repository; 
  
    @Autowired
    private PasswordEncoder encoder;
  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
  
        Optional<UserInfo> userDetail = repository.findByName(username); 
  
        // Converting userDetail to UserDetails 
        return userDetail.map(UserInfoDetails::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
    } 
  
    public UserInfo addUserWithImage(UserInfo userInfo, MultipartFile file) throws IOException {
        userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
        // userInfo.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        if (file != null) {
            userInfo.setImage(ImageUtils.compressImage(file.getBytes()));
        }
        return repository.save(userInfo); 
    }
    
    public UserInfo uploadImage(int id, MultipartFile file) throws IOException {
    	UserInfo userInfo = repository.findById(id);
        if (file != null) {
        	userInfo.setImage(ImageUtils.compressImage(file.getBytes()));
        }
        return repository.save(userInfo); 
    }

    public byte[] getImage(int id) throws IOException {
    	UserInfo userInfo = repository.findById(id);
    	byte[] imageBytes = ImageUtils.decompressImage(userInfo.getImage()); // java.util.Base64.getDecoder().decode(userInfo.getImage());
        
    	return imageBytes; 
    }
    
    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
        UserInfo usinfo = repository.save(userInfo);
        
        return usinfo;
    } 
    
    public UserInfo getListById(long id) { 
        return repository.findById(id);
    } 
    
    public List<UserInfo> getListWithPagination(int pagesize, int pagenumber) {
    	int offset = (pagenumber - 1) * pagesize;
        return repository.findAllWithPagination(offset, pagesize);
    }
    
    public Page<UserInfo> getProfileWithPaginationAndSorting(int pageSize, int offset, String field){
        Page<UserInfo> products = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }
  
}