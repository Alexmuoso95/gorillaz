package com.gorillaz.core.service;

import java.util.List;

import com.gorillaz.core.model.entity.Product;

public interface ProductService {
	public Product getProduct(Long id);
	public List<Product> getProducts();
	public Product createProduct(Product product);
	public Product updateProduct(Product product, Long id);
	public void deleteProduct(Long id);
}
