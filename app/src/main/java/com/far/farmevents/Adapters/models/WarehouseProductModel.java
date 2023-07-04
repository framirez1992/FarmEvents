package com.far.farmevents.Adapters.models;

import com.far.farmevents.Models.WarehouseProduct;

public class WarehouseProductModel {

    WarehouseProduct warehouseProduct;
    String code;
    String description;
    int quantity;

    public  WarehouseProductModel(WarehouseProduct warehouseProduct, int quantity){
        this.warehouseProduct = warehouseProduct;
        this.code = warehouseProduct.getCode();
        this.description = warehouseProduct.getDescription();
        this.quantity = quantity;
    }

    public WarehouseProduct getWarehouseProduct() {
        return warehouseProduct;
    }

    public void setWarehouseProduct(WarehouseProduct warehouseProduct) {
        this.warehouseProduct = warehouseProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
