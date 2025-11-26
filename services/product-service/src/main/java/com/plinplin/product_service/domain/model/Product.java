package com.plinplin.product_service.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private Long categoryId;
    private String status;
}
