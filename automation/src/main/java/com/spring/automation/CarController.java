package com.spring.automation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/cardealer")
public class CarController {
	@Autowired
	private CarService carService;
	
	@PostMapping(value = "/car")
	public Car createCar(@RequestBody Car car) { //@RequestBody means the parameter is being passed as argument by httpclient
		return carService.createCar(car);
	}
	@GetMapping(value = "/car")
	public List<Car> getAllCar() {
		return carService.getAllCar();
	}
	@GetMapping(value = "/car/{id}")
	public ResponseEntity<Car>  getCarById(@PathVariable("id") Integer id) { //@Pathvariable means the parameter's value is taken from the url
		return carService.getCarById(id);
	}
	@PutMapping(value = "/car/{id}")
	public ResponseEntity<Car>  updateCar(@PathVariable("id") Integer id,@RequestBody Car car) {
		return carService.updateCar(id, car);
	}
	@DeleteMapping(value = "/car/{id}")
	public void deleteCarById(@PathVariable("id") Integer id) {
		carService.deleteCarById(id);
	}
	
}
