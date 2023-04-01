package com.joalvarez.wharehousecontrol.product.data.dto;

import com.joalvarez.wharehousecontrol.general.data.dto.BaseDTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductDTO implements BaseDTO {

    private long id;
    @NotBlank(message = "Debe ingresar un codigo de producto.")
    private String sku;
    private String description;
    @Min(value = 0, message = "El precio no debe ser menor a 0.")
    private double price;
    private boolean active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
