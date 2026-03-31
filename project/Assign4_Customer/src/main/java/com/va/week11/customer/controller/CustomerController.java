package com.va.week11.customer.controller;

import com.va.week11.customer.entity.CustomerEntity;
import com.va.week11.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customers";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new CustomerEntity());
        return "addCustomer";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute CustomerEntity customer, Model model) {
        customerRepository.save(customer);
        model.addAttribute("message", "Customer '" + customer.getFirstName() + " " + customer.getLastName() + "' added successfully!");
        model.addAttribute("customer", new CustomerEntity());
        return "addCustomer";
    }
}
