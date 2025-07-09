package com.example.carrental.service;

import com.example.carrental.model.Payment;
import com.example.carrental.repository.PaymentRepository;
import com.example.carrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private RentalRepository rentalRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    public long getTotalRentals() {
        return rentalRepo.count();
    }

    public double getTotalRevenue() {
        return paymentRepo.findAll().stream()
                .filter(p -> p.getStatus().equalsIgnoreCase("PAID"))
                .mapToDouble(Payment::getAmount)
                .sum();
    }
}
