package com.example.demo.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "OrderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    private Product product;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @Column(name = "UnitPrice", nullable = false)
    private BigDecimal unitPrice;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    @JsonProperty("OrderId")
    public Long getOrderId() {
        return this.order != null ? this.order.getId() : null;
    }
    
    
    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
    

}