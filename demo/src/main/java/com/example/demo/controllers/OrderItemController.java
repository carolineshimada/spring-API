package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.OrderItem;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.OrderItemRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("orderItem")
public class OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Operation(summary = "Return a list of all order items")
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
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Operation(summary = "Return an order item by ID")
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
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        return orderItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id " + id));
    }

    @Operation(summary = "Create a new order item")
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
    public ResponseEntity<OrderItem> createOrderItem(@Valid @RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderItem);
    }

    @Operation(summary = "Update an order item by ID")
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
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @Valid @RequestBody OrderItem orderItemDetails) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id " + id));

        orderItem.setOrder(orderItemDetails.getOrder());
        orderItem.setProduct(orderItemDetails.getProduct());
        orderItem.setQuantity(orderItemDetails.getQuantity());
        orderItem.setUnitPrice(orderItemDetails.getUnitPrice());

        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @Operation(summary = "Delete an order item by ID")
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
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id " + id));

        orderItemRepository.delete(orderItem);
        return ResponseEntity.noContent().build();
    }
}
