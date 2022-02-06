package com.ecom.productcatalog.service;

import com.ecom.productcatalog.DTO.ProductDTO;
import com.ecom.productcatalog.entity.Product;

import java.util.List;


public interface ProductService {
    public List<Product> getAllProduct();
    public List<Product> getAllProductByCategory(String category);
    public ProductDTO getProductById(Long id);
    public List<Product> getAllProductsByName(String name);
    public Product addProduct(Product product);
    Product updateProduct(Product product);
    public void deleteProduct(Long productId);
}

