package com.example.demo.controllers;
import io.swagger.v3.oas.annotations.media.Content;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Product;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Operation(summary = "Return a list of all products")
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
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Operation(summary = "Return a product by ID")
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
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    @Operation(summary = "Create a new product")
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
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @Operation(summary = "Update a product by ID")
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
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @Operation(summary = "Delete a product by ID")
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
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
