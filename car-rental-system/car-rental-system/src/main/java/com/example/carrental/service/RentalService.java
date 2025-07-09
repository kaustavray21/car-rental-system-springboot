package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.model.Rental;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepo;

    @Autowired
    private CarRepository carRepo;

    public Rental createRental(Rental rental) {
        Car car = rental.getCar();
        if (!car.isAvailable()) {
            throw new RuntimeException("Car is not available for rental");
        }

        // Mark the car as not available
        car.setAvailable(false);
        carRepo.save(car);

        return rentalRepo.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalRepo.findAll();
    }

    public void deleteRental(Long id) {
        Rental rental = rentalRepo.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
        Car car = rental.getCar();
        car.setAvailable(true); // make car available again
        carRepo.save(car);
        rentalRepo.deleteById(id);
    }

    public Rental getRentalById(Long id) {
        return rentalRepo.findById(id).orElseThrow(() -> new RuntimeException("Rental not found with id " + id));
    }
}
