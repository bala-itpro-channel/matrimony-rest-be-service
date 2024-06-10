package com.javatalent.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javatalent.entity.Photo;
import com.javatalent.entity.UserInfo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> { 

}