package com.example.demo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.OrderItem;
import com.example.demo.repositories.OrderItemRepository;

@RestController
@RequestMapping("orderItem")
public class OrderItemController {
	
	 @Autowired
	 private OrderItemRepository orderItemRepository;
	 
	@GetMapping
	public List<OrderItem> getAllOrders() {
	    return orderItemRepository.findAll();
	}
}




