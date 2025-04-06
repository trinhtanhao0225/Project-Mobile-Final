package com.example.banquanao.Model;

import java.io.Serializable;

public class ColorQuantity implements Serializable {
    private int id;
    private String size;
    private String color;
    private int quantity;

    public ColorQuantity(int id, String size, String color, int quantity) {
        this.id = id;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public ColorQuantity(String size, int quantity, String color) {
        this.size = size;
        this.quantity = quantity;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
