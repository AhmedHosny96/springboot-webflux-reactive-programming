package com.hosny.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductsDto {

    private int id;
    private String name;
    private int quantity;
    private double price;
}
