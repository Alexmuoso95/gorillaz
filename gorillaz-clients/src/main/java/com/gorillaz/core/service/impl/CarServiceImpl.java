package com.gorillaz.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gorillaz.core.model.entity.Car;
import com.gorillaz.core.repository.CarDAO;
import com.gorillaz.core.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarDAO carDao;
	

	@Override
	public Car getCar(Long id) {
		return null;
	}

	@Override
	public List<Car> getCars() {
		return null;
	}

	@Override
	public Car createCar(Car car) {
		carDao.save(car);
		return null;
	}

	@Override
	public Car updateCar(Car car, Long id) {
		return null;
	}

	@Override
	public void deleteCar(Long id) {
	}

}
