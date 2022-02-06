package com.ecom.recommendation.controller;


import com.ecom.recommendation.DTO.ProductDTO;
import com.ecom.recommendation.DTO.RecommendationDTO;
import com.ecom.recommendation.DTO.UserDTO;
import com.ecom.recommendation.feignClient.ProductClient;
import com.ecom.recommendation.feignClient.UserClient;
import com.ecom.recommendation.http.header.HeaderGenerator;
import com.ecom.recommendation.model.Recommendation;
import com.ecom.recommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;
    
    @Autowired
    private HeaderGenerator headerGenerator;

//    @GetMapping(value = "/getByProductName")
//    private ResponseEntity<List<Recommendation>> getAllRating(@RequestParam("name") String productName){
//        List<Recommendation> recommendations = recommendationService.getAllRecommendationByProductName(productName);
//        if(!recommendations.isEmpty()) {
//        	return new ResponseEntity<List<Recommendation>>(
//        		recommendations,
//        		headerGenerator.getHeadersForSuccessGetMethod(),
//        		HttpStatus.OK);
//        }
//        return new ResponseEntity<List<Recommendation>>(
//        		headerGenerator.getHeadersForError(),
//        		HttpStatus.NOT_FOUND);
////    }
    
    @PostMapping(value = "/save")
    private ResponseEntity<Recommendation> saveRecommendations(
            @RequestBody RecommendationDTO recommendationDTO,
            HttpServletRequest request){

    	
    	ProductDTO product = productClient.getProductById(recommendationDTO.getProductId());
		UserDTO user = userClient.getUserById(recommendationDTO.getUserId());
    	
		if(product != null && user != null) {
			try {
				Recommendation recommendation = new Recommendation();
				recommendation.setProductId(product.getId());
				recommendation.setProductId(user.getId());
				recommendation.setRating(recommendationDTO.getRating());
				recommendation.setText(recommendationDTO.getText());
				recommendationService.saveRecommendation(recommendation);
				return new ResponseEntity<Recommendation>(
						recommendation,
						headerGenerator.getHeadersForSuccessPostMethod(request, recommendation.getId()),
						HttpStatus.CREATED);
			}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Recommendation>(
						headerGenerator.getHeadersForError(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
        return new ResponseEntity<Recommendation>(
        		headerGenerator.getHeadersForError(),
        		HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity<Void> deleteRecommendations(@PathVariable("id") Long id){
    	Recommendation recommendation = recommendationService.getRecommendationById(id);
    	if(recommendation != null) {
    		try {
    			recommendationService.deleteRecommendation(id);
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
    	return new ResponseEntity<Void>(
    			headerGenerator.getHeadersForError(),
    			HttpStatus.NOT_FOUND);
    }
}
