package com.gorillaz.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorillaz.core.model.entity.UserDTO;

public interface UserRepository extends JpaRepository<UserDTO, Long> {
	
	public UserDTO findByName (String name);
//	@Query("select u from UserDTO u where u.Email like ?1")
//	public UserDTO findByUserEmail(String email);
}
