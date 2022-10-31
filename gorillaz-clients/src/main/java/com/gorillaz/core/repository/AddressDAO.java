package com.gorillaz.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorillaz.core.model.entity.Address;


public interface AddressDAO extends JpaRepository<Address,Long>{
	
	public List<Address> findAllByClientId(Long clientId);
}
