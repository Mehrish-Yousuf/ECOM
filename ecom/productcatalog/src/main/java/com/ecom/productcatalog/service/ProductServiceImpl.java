package com.ecom.productcatalog.service;

import com.ecom.productcatalog.entity.Product;
import com.ecom.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productTemp = productRepository.findById(id);

        return productTemp.get();
    }

    @Override
    public List<Product> getAllProductsByName(String name) {
        return productRepository.findAllByProductName(name);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Product product) {
        Optional<Product> product1 = productRepository.findById(product.getId());
        if (product1.get()!=null){
            try{
                productRepository.save(product);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
       return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
