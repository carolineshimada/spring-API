package com.example.demo.controllers;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import com.example.demo.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Operation(summary = "Return a list of all customers")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
    	    @ApiResponse(responseCode = "201", description = "Created - The resource was successfully created"),
    	    @ApiResponse(responseCode = "400", description = "Bad Request - The request could not be understood or was missing required parameters"),
    	    @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication failed or user does not have permissions for the desired action"),
    	    @ApiResponse(responseCode = "403", description = "Forbidden - Authentication succeeded but authenticated user does not have access to the resource"),
    	    @ApiResponse(responseCode = "404", description = "Not Found - The resource was not found"),
    	    @ApiResponse(responseCode = "500", description = "Internal Server Error - An error occurred on the server"),
    	    @ApiResponse(responseCode = "503", description = "Service Unavailable - The service is not available")
    	})
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Operation(summary = "Return a customer by ID")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
    	    @ApiResponse(responseCode = "201", description = "Created - The resource was successfully created"),
    	    @ApiResponse(responseCode = "400", description = "Bad Request - The request could not be understood or was missing required parameters"),
    	    @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication failed or user does not have permissions for the desired action"),
    	    @ApiResponse(responseCode = "403", description = "Forbidden - Authentication succeeded but authenticated user does not have access to the resource"),
    	    @ApiResponse(responseCode = "404", description = "Not Found - The resource was not found"),
    	    @ApiResponse(responseCode = "500", description = "Internal Server Error - An error occurred on the server"),
    	    @ApiResponse(responseCode = "503", description = "Service Unavailable - The service is not available")
    	})
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + id));
    }

    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
    	    @ApiResponse(responseCode = "201", description = "Created - The resource was successfully created"),
    	    @ApiResponse(responseCode = "400", description = "Bad Request - The request could not be understood or was missing required parameters"),
    	    @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication failed or user does not have permissions for the desired action"),
    	    @ApiResponse(responseCode = "403", description = "Forbidden - Authentication succeeded but authenticated user does not have access to the resource"),
    	    @ApiResponse(responseCode = "404", description = "Not Found - The resource was not found"),
    	    @ApiResponse(responseCode = "500", description = "Internal Server Error - An error occurred on the server"),
    	    @ApiResponse(responseCode = "503", description = "Service Unavailable - The service is not available")
    	})
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @Operation(summary = "Update an existing customer by ID")
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
    	    @ApiResponse(responseCode = "201", description = "Created - The resource was successfully created"),
    	    @ApiResponse(responseCode = "400", description = "Bad Request - The request could not be understood or was missing required parameters"),
    	    @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication failed or user does not have permissions for the desired action"),
    	    @ApiResponse(responseCode = "403", description = "Forbidden - Authentication succeeded but authenticated user does not have access to the resource"),
    	    @ApiResponse(responseCode = "404", description = "Not Found - The resource was not found"),
    	    @ApiResponse(responseCode = "500", description = "Internal Server Error - An error occurred on the server"),
    	    @ApiResponse(responseCode = "503", description = "Service Unavailable - The service is not available")
    	})
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
    @ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Successfully retrieved"), 
    	    @ApiResponse(responseCode = "201", description = "Created - The resource was successfully created"),
    	    @ApiResponse(responseCode = "400", description = "Bad Request - The request could not be understood or was missing required parameters"),
    	    @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication failed or user does not have permissions for the desired action"),
    	    @ApiResponse(responseCode = "403", description = "Forbidden - Authentication succeeded but authenticated user does not have access to the resource"),
    	    @ApiResponse(responseCode = "404", description = "Not Found - The resource was not found"),
    	    @ApiResponse(responseCode = "500", description = "Internal Server Error - An error occurred on the server"),
    	    @ApiResponse(responseCode = "503", description = "Service Unavailable - The service is not available")
    	})
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
