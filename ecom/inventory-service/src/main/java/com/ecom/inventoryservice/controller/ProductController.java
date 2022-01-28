package com.ecom.inventoryservice.controller;

import com.ecom.inventoryservice.entity.ProductInventory;
import com.ecom.inventoryservice.header.HeaderGenerator;
import com.ecom.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class ProductController {

    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping (value = "/products")
    public ResponseEntity<List<ProductInventory>> getAllProducts(){
        List<ProductInventory> products =  inventoryService.getAllProduct();
        if(!products.isEmpty()) {
        	return new ResponseEntity<List<ProductInventory>>(
        			products,
        			headerGenerator.getHeadersForSuccessGetMethod(),
        			HttpStatus.OK);
        }
        return new ResponseEntity<List<ProductInventory>>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);       
    }

    @GetMapping(value = "/productsByCategory/{category}")
    public ResponseEntity<List<ProductInventory>> getAllProductByCategory(@PathVariable ("category") String category){
        List<ProductInventory> products = inventoryService.getAllProductByCategory(category);
        if(!products.isEmpty()) {
        	return new ResponseEntity<List<ProductInventory>>(
        			products,
        			headerGenerator.getHeadersForSuccessGetMethod(),
        			HttpStatus.OK);
        }
        return new ResponseEntity<List<ProductInventory>>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/productById/{id}")
    public ResponseEntity<ProductInventory> getOneProductById(@PathVariable ("id") long id){
        ProductInventory product =  inventoryService.getProductById(id);
        if(product != null) {
        	return new ResponseEntity<ProductInventory>(
        			product,
        			headerGenerator.getHeadersForSuccessGetMethod(),
        			HttpStatus.OK);
        }
        return new ResponseEntity<ProductInventory>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/productByName", params = "name")
    public ResponseEntity<List<ProductInventory>> getAllProductsByName(@RequestParam ("name") String name){
        List<ProductInventory> products =  inventoryService.getAllProductsByName(name);
        if(!products.isEmpty()) {
        	return new ResponseEntity<List<ProductInventory>>(
        			products,
        			headerGenerator.getHeadersForSuccessGetMethod(),
        			HttpStatus.OK);
        }
        return new ResponseEntity<List<ProductInventory>>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.NOT_FOUND);
    }
}
