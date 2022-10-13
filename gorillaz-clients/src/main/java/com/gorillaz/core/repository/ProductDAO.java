package com.gorillaz.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gorillaz.core.model.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Long>{

}
