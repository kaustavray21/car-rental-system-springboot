package com.example.carrental.service;

import com.example.carrental.model.Payment;
import com.example.carrental.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepo;

    public Payment makePayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepo.findById(id).orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
    }

    public void deletePayment(Long id) {
        paymentRepo.deleteById(id);
    }
}
