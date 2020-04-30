package com.example.shopping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private int id;
    private int code;
    private int price;
    private String name;

}
