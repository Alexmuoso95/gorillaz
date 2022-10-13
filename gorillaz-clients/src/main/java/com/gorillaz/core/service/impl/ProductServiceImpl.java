package com.gorillaz.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorillaz.core.model.entity.Product;
import com.gorillaz.core.repository.ProductDAO;
import com.gorillaz.core.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDao;
	
	@Override
	public Product getProduct(Long id) {
		return productDao.findById(id).orElse(null);
	}

	@Override
	public List<Product> getProducts() {
		return productDao.findAll();
	}

	@Override
	public Product createProduct(Product product) {
		return productDao.save(product);
	}

	@Override
	public Product updateProduct(Product product, Long id) {
		return productDao.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productDao.deleteById(id);
	}

}
