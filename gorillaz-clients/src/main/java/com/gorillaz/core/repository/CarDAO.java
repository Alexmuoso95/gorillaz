package com.gorillaz.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorillaz.core.model.entity.Car;

public interface CarDAO extends JpaRepository<Car, Long>{

}
