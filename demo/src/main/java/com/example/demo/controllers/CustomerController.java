package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import com.example.demo.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Operation(summary = "Retrieve a list of all customers")
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Operation(summary = "Retrieve a customer by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    @Operation(summary = "Create a new customer")
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @Operation(summary = "Update an existing customer by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customerDetails) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerDetails.getName());
                    customer.setEmail(customerDetails.getEmail());
                    customer.setPhone(customerDetails.getPhone());
                    customer.setAddress(customerDetails.getAddress());
                    customer.setPassword(customerDetails.getPassword());
                    Customer updatedCustomer = customerRepository.save(customer);
                    return ResponseEntity.ok(updatedCustomer);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    @Operation(summary = "Delete a customer by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customerRepository.delete(customer);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }
}
