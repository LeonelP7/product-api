package com.example.demo.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @Column
    private UUID id;

    private Double total;
}
