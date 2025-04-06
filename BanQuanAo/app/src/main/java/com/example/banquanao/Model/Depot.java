package com.example.banquanao.Model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Depot implements Serializable {
    private int id;

    private Type type; // Nếu server trả về type

    private String name;
    private String image;
    private String description;

    private List<ColorQuantity> stock; // Đổi thành List thay vì Map

    private BigDecimal price;

    @SerializedName("importDate")
    private String importDate;

    public Depot(int id, Type type, String name, String image, String description, List<ColorQuantity> stock, BigDecimal price, String importDate) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.image = image;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.importDate = importDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ColorQuantity> getStock() {
        return stock;
    }

    public void setStock(List<ColorQuantity> stock) {
        this.stock = stock;
    }



    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

}
