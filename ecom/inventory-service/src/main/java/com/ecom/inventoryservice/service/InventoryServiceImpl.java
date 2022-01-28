package com.ecom.inventoryservice.service;

import com.ecom.inventoryservice.entity.ProductInventory;
import com.ecom.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<ProductInventory> getAllProduct() {
        return inventoryRepository.findAll();
    }

    @Override
    public List<ProductInventory> getAllProductByCategory(String category) {
        return inventoryRepository.findAllByCategory(category);
    }

    @Override
    public ProductInventory getProductById(Long id) {
        Optional<ProductInventory> productTemp = inventoryRepository.findById(id);

        return productTemp.get();
    }

    @Override
    public List<ProductInventory> getAllProductsByName(String name) {
        return inventoryRepository.findAllByProductName(name);
    }

    @Override
    public ProductInventory addProduct(ProductInventory product) {
        return inventoryRepository.save(product);
    }


    @Override
    public ProductInventory updateProduct(ProductInventory product) {
        Optional<ProductInventory> product1 = inventoryRepository.findById(product.getId());
        if (product1.get()!=null){
            try{
                inventoryRepository.save(product);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
       return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        inventoryRepository.deleteById(productId);
    }
}
