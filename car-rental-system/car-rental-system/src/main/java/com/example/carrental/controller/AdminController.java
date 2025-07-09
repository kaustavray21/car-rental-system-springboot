package com.example.carrental.controller;

import com.example.carrental.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/summary")
    public String getSystemSummary() {
        long totalRentals = adminService.getTotalRentals();
        double totalRevenue = adminService.getTotalRevenue();
        return "📊 Total Rentals: " + totalRentals + " | 💰 Total Revenue: ₹" + totalRevenue;
    }
}
