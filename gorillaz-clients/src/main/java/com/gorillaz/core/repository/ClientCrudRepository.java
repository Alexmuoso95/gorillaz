package com.gorillaz.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gorillaz.core.entity.Client;

@Repository
public interface ClientCrudRepository extends CrudRepository<Client, Long> {

}
