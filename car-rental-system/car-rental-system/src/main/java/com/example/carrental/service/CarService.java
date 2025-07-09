package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepo;

    public Car addCar(Car car) {
        car.setAvailable(true);
        return carRepo.save(car);
    }

    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car existingCar = carRepo.findById(id).orElseThrow(() -> new RuntimeException("Car not found with id " + id));
        existingCar.setBrand(updatedCar.getBrand());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setAvailable(updatedCar.isAvailable());
        return carRepo.save(existingCar);
    }

    public void deleteCar(Long id) {
        carRepo.deleteById(id);
    }

    public List<Car> getAvailableCars() {
        return carRepo.findByAvailable(true);
    }
}
