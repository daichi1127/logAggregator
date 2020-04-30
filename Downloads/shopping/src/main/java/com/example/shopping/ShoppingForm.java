package com.example.shopping;

import lombok.Data;

@Data
public class ShoppingForm {
    private int id;
    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
