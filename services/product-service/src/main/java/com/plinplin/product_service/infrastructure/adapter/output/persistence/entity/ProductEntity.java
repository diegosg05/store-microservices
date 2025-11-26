package com.plinplin.product_service.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {
    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private Long categoryId;
    private String status;
}
