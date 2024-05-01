package com.javatalent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javatalent.entity.Profile;
import com.javatalent.repo.ProfileRepository;
import com.javatalent.exception.ResourceNotFoundException;
@RestController
@RequestMapping("/api")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
public class ProfileController {
	@Autowired
	ProfileRepository repo;
	
	@GetMapping("/profilebyname")
	public ResponseEntity<List<Profile>> getAllTutorials(@RequestParam(required = false) String name) {
		try {
			List<Profile> profiles = new ArrayList<Profile>();
			if (name == "")
				repo.findAll().forEach(profiles::add);
			else
				repo.findByNameContaining(name).forEach(profiles::add);

			if (profiles.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(profiles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/profiles") 
	public List<Profile> getAllProfiles() {
		return repo.findAll();
	}
	
	@GetMapping("/profilessql")
	public List<Profile> getProfilesNativeSql() {
		return repo.getProfilesNativeSql();
	}
	
	@GetMapping("/profilessql1")
	public List<Profile> getProfilesNativeSql1() {
		return repo.getProfilesNativeSql1();
	}
	
	
	@GetMapping("/profiles/{id}")
	public Profile getProfileById(@PathVariable(value = "id") int id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));
	}
	
	@PostMapping("/profiles")
	public Profile createProfile(Profile profile) {
		return repo.save(profile);
	}
	
	@PutMapping("/profiles/{id}")
	public Profile updateProfile(Profile profileObj, @PathVariable(value = "id") int id) {
		Profile profile = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));
		
		profile.setEducation(profileObj.getEducation());
		profile.setName(profileObj.getName());
		
		Profile updatedProfile = repo.save(profile);
		return updatedProfile;
	}
	
	@DeleteMapping("/profiles/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") int id) {
        Profile profile = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));

        repo.delete(profile);

        return ResponseEntity.ok().build();
    }
}
