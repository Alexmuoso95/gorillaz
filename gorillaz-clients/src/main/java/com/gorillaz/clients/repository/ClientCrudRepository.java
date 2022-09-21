package com.gorillaz.clients.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gorillaz.clients.entity.Client;

@Repository
public interface ClientCrudRepository extends CrudRepository<Client, Long> {

}
