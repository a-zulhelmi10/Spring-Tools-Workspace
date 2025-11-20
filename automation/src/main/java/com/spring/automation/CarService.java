package com.spring.automation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;
	
	public Car createCar(Car car) {
		return carRepository.save(car);
	}
	public List<Car> getAllCar() {
		return carRepository.findAll(); 
	}
	public ResponseEntity<Car> getCarById(Integer id) {
		Car car = carRepository.findById(id).orElseThrow(()->new CarNotFoundException("Car with owner id " +id +"does not found"));
		return ResponseEntity.ok(car);
	}
	public ResponseEntity<Car> updateCar(Integer id, Car carDetails) {
		Car car1 = carRepository.findById(id).orElseThrow(()->new CarNotFoundException("Car with owner id " +id +"does not found"));
		car1.setOwner(carDetails.getOwner());
		car1.setBrand(carDetails.getBrand());
		car1.setModel(carDetails.getModel());
		car1.setPrice(carDetails.getPrice());
		car1.setDepo(carDetails.getDepo());
		car1.setMonths(carDetails.getMonths());
		carRepository.save(car1);
		return ResponseEntity.ok(car1);
	}
	public ResponseEntity<Map<String,Boolean>> deleteCarById(Integer id) {
		Car car = carRepository.findById(id).orElseThrow(()->new CarNotFoundException("Car with owner id " +id +"does not found"));
		carRepository.delete(car);
		Map<String,Boolean> response = new HashMap<String,Boolean>();
		response.put("Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
