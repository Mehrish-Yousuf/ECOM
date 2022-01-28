package com.ecom.inventoryservice.repository;

import com.ecom.inventoryservice.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<ProductInventory, Long> {
    public List<ProductInventory> findAllByCategory(String category);
    public List<ProductInventory> findAllByProductName(String name);
}
