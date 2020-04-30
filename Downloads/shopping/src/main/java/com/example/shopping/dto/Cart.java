package com.example.shopping.dto;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private int total;
    private int totalPrice;
    private List<Item> items;

}
