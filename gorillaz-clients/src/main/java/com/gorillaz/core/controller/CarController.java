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

import com.gorillaz.core.model.entity.Car;
import com.gorillaz.core.model.response.ResponseMessage;
import com.gorillaz.core.service.CarService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/clients/cars")
@CrossOrigin(origins= {"http://localhost:4200"})
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@PostMapping
	public ResponseEntity<?> createCar(@RequestBody Car car){
		return new ResponseEntity<>(carService.createCar(car),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getCars(){
		return new ResponseEntity<>(carService.getCars(),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCar(@PathVariable Long id){
		Car car = carService.getCar(id);
		if(car==null) {
			 return new ResponseEntity<>(new ResponseMessage("CARRO con el id ".concat(id.toString()).concat(" no existe."),404),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(carService.getCar(id),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatecar(@Valid @RequestBody Car car, @PathVariable Long id){
		return new ResponseEntity<>(carService.updateCar(car,id),HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletecar(@PathVariable Long id) {
		log.info("CARRO DELETED ");
		carService.deleteCar(id);
		return new ResponseEntity<>(new ResponseMessage("CARRO id ".concat(id.toString()).concat(" fue eliminada con exito."), 200), HttpStatus.OK);
	}

}
