package com.gorillaz.core.service;

import java.util.List;

import com.gorillaz.core.model.entity.Car;

public interface CarService {

	public Car getCar(Long id);
	public List<Car> getCars();
	public Car createCar(Car car);
	public Car updateCar(Car car, Long id);
	public void deleteCar(Long id);
	
}
