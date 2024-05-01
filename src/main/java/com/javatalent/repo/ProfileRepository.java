package com.javatalent.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatalent.entity.Profile;


public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	// List<Profile> findByAge(Integer age);
	List<Profile> findByNameContaining(String name);
	
	//Native SQL (Table name profile)
	@Query(nativeQuery = true, value = "SELECT * FROM profile")
	public List<Profile> getProfilesNativeSql();
	
	//JPQL (Entity class Name Profile)
	@Query("select p from Profile p where p.profileId > 1")
	public List<Profile> getProfilesNativeSql1();
	
//	@Query(nativeQuery = true, value = "select profile_id, name, age, education from profile")
//	public List<Profile> getProfilesNativeSql1();
	
	//	@Query(nativeQuery = true,
	//            value = "SELECT transiction.user_id, transiction.quantity,transiction.instrument_name, transiction.Price,instrument.LTP"
	//            + "FROM instrument"
	//            + "INNER JOIN transiction"
	//            + "ON instrument.instrument=transiction.instrument_name")
	//    public List<Object[]> getTransictionsAndInstruments();
		
	// Many to many https://vladmihalcea.com/the-best-way-to-map-a-many-to-many-association-with-extra-columns-when-using-jpa-and-hibernate/
	
}
