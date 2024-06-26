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
	
	@Column
	private String onBehalfOf;
	
	@Column(name = "name")
	private String name;
	
	@Column
	@NotEmpty
	@Size(max=50,message = "Education must be max of 50 characters")
	// @JsonProperty(access = Access.WRITE_ONLY)
	private String education;
	
	@Column
	private String gender;
	
	@Column
	private String fatherName;
	
	@Column
	private String motherName;
	
	@Column
	private String dob;
	
	@Column
	private String job;
	
	@Column
	private int income;
	
	@Column
	private String houseName;
	
	@Column
	private String branch;
	@Column
	private String temple;
	
	@Column
	private String femaleGod;
	@Column
	private String star;
	@Column
	private String zodiac;
	@Column
	private String nativePlace;
	@Column
	private String currentPlace;

	@Column
	private String dosham;
	@Column
	private String marritalStatus;
	@Column
	private String height;
	@Column
	private String details;
	@Column
	private String mobile;
	@Column
	private String email;
	@Column
	private String password;
	
	public String getOnBehalfOf() {
		return onBehalfOf;
	}

	public void setOnBehalfOf(String onBehalfOf) {
		this.onBehalfOf = onBehalfOf;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDosham() {
		return dosham;
	}

	public void setDosham(String dosham) {
		this.dosham = dosham;
	}

	public String getMarritalStatus() {
		return marritalStatus;
	}

	public void setMarritalStatus(String marritalStatus) {
		this.marritalStatus = marritalStatus;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Profile() {}

	public Profile(int profileId, String name,
			@NotEmpty @Size(max = 50, message = "Education must be max of 50 characters") String education, int age,
			String fatherName, String motherName, String dob, String job, int income, String houseName, String branch,
			String temple, String femaleGod, String star, String zodiac, String nativePlace, String currentPlace,
			String mobile, String email) {
		super();
		this.profileId = profileId;
		this.name = name;
		this.education = education;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.dob = dob;
		this.job = job;
		this.income = income;
		this.houseName = houseName;
		this.branch = branch;
		this.temple = temple;
		this.femaleGod = femaleGod;
		this.star = star;
		this.zodiac = zodiac;
		this.nativePlace = nativePlace;
		this.currentPlace = currentPlace;
		this.mobile = mobile;
		this.email = email;
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
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getTemple() {
		return temple;
	}
	public void setTemple(String temple) {
		this.temple = temple;
	}
	public String getFemaleGod() {
		return femaleGod;
	}
	public void setFemaleGod(String femaleGod) {
		this.femaleGod = femaleGod;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getCurrentPlace() {
		return currentPlace;
	}
	public void setCurrentPlace(String currentPlace) {
		this.currentPlace = currentPlace;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
