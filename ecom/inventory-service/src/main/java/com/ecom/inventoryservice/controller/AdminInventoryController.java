package com.ecom.inventoryservice.controller;

import com.ecom.inventoryservice.entity.ProductInventory;
import com.ecom.inventoryservice.header.HeaderGenerator;
import com.ecom.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/products")
public class AdminInventoryController {

    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private HeaderGenerator headerGenerator;

    @PostMapping(value = "/add")
    private ResponseEntity<ProductInventory> addProduct(@RequestBody ProductInventory inv, HttpServletRequest request){
    	if(inv != null) {
    		try {
    			inventoryService.addProduct(inv);
    	        return new ResponseEntity<ProductInventory>(
    	        		inv,
    	        		headerGenerator.getHeadersForSuccessPostMethod(request, inv.getId()),
    	        		HttpStatus.CREATED);
    		}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<ProductInventory>(
						headerGenerator.getHeadersForError(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
    	}
    	return new ResponseEntity<ProductInventory>(
    			headerGenerator.getHeadersForError(),
    			HttpStatus.BAD_REQUEST);       
    }

	@PutMapping(value="/update")
	public ResponseEntity<Boolean> updateProduct(@RequestBody ProductInventory product) {

		inventoryService.updateProduct(product);
		return new ResponseEntity<Boolean>(headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
	}
    
    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
    	ProductInventory product = inventoryService.getProductById(id);
    	if(product != null) {
    		try {
    			inventoryService.deleteProduct(id);
    	        return new ResponseEntity<Void>(
    	        		headerGenerator.getHeadersForSuccessGetMethod(),
    	        		HttpStatus.OK);
    		}catch (Exception e) {
				e.printStackTrace();
    	        return new ResponseEntity<Void>(
    	        		headerGenerator.getHeadersForError(),
    	        		HttpStatus.INTERNAL_SERVER_ERROR);
			}
    	}
    	return new ResponseEntity<Void>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);      
    }
}
