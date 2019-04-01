package com.lozdarski.coachasistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lozdarski.coachasistant.entity.UserEntity;


public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	
	@Query(value = "SELECT u FROM UserEntity u WHERE userName = ?1")
	List<UserEntity> findUsersByUsername(String userName);
	
	@Query(value = "SELECT u FROM UserEntity u WHERE email = ?1")
	List<UserEntity> findUsersByEmail(String email);

}