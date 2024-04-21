package com.javatalent.entity;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="profile")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileId;
	
	@Column(name = "name")
	private String name;
	
	@Column
	@NotEmpty
	@Size(max=50,message = "Education must be max of 50 characters")
	// @JsonProperty(access = Access.WRITE_ONLY)
	private String education;
	
	@Column
	private int age;
	
	public Profile() {
		
	}
	public Profile(String name, String education, int age) {
		super();
		this.name = name;
		this.education = education;
		this.age = age;
	}
	
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Profile [profileId=" + profileId + ", name=" + name + ", education=" + education + ", age=" + age + "]";
	}
	
}
