package com.gorillaz.core.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorillaz.core.model.entity.Product;
import com.gorillaz.core.model.response.ResponseMessage;
import com.gorillaz.core.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/products")
@CrossOrigin(origins= {"http://localhost:4200"})
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody Product product){
		return new ResponseEntity<>(productService.createProduct(product),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getProducts(){
		return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable Long id){
		Product Product = productService.getProduct(id);
		if(Product==null) {
			 return new ResponseEntity<>(new ResponseMessage("Producto con el id ".concat(id.toString()).concat(" no existe."),404),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, @PathVariable Long id){
		return new ResponseEntity<>(productService.updateProduct(product,id),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		log.info("Product DELETED ");
		productService.deleteProduct(id);
		return new ResponseEntity<>(new ResponseMessage("Producto id ".concat(id.toString()).concat(" fue eliminada con exito."), 200), HttpStatus.OK);
	}

}
