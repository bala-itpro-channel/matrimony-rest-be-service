package com.javatalent.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javatalent.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> { 
    Optional<UserInfo> findByName(String username);
    UserInfo findOneByName(String username);
    
    UserInfo findByFirstName(String firstName);

    List<UserInfo> findByEmail(String email);

    List<UserInfo> findOneByFirstName(String lastName);
    
    @Query("SELECT u FROM UserInfo u WHERE u.id = (:id)")
    UserInfo findById(@Param("id") long id);
    
    //Find by gender
    //Find by Age
    //Find by Location
    
    @Query(value="SELECT * FROM UserInfo u limit :pagesize offset :offset", nativeQuery = true)
    List<UserInfo> findAllWithPagination(@Param("offset") int offset, @Param("pagesize") int pagesize);
}