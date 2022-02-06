package com.ecom.recommendation.repository;

import com.ecom.recommendation.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

//    @Query("select r FROM Recommendation r WHERE r.productId = :productName")
//    public List<Recommendation> findAllRatingByProductName(@Param("productName") String productName);
}
