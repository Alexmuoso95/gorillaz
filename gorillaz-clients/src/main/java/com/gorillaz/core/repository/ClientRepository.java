package com.gorillaz.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gorillaz.core.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


}
