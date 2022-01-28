package com.ecom.inventoryservice.service;

import com.ecom.inventoryservice.entity.ProductInventory;

import java.util.List;


public interface InventoryService {
    public List<ProductInventory> getAllProduct();
    public List<ProductInventory> getAllProductByCategory(String category);
    public ProductInventory getProductById(Long id);
    public List<ProductInventory> getAllProductsByName(String name);
    public ProductInventory addProduct(ProductInventory product);
    ProductInventory updateProduct(ProductInventory product);
    public void deleteProduct(Long productId);
}

