package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}