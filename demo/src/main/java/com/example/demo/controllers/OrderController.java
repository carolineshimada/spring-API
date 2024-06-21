package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Order;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.OrderRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Operation(summary = "Return a list of all orders")
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
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Operation(summary = "Return an order by ID")
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
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    @Operation(summary = "Create a new order")
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
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @Operation(summary = "Update an order by ID")
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
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @Valid @RequestBody Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        order.setCustomer(orderDetails.getCustomer());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setTotal(orderDetails.getTotal());
        order.setStatus(orderDetails.getStatus());
        order.setOrderItems(orderDetails.getOrderItems());

        Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @Operation(summary = "Delete an order by ID")
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
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        orderRepository.delete(order);
        return ResponseEntity.noContent().build();
    }
}
