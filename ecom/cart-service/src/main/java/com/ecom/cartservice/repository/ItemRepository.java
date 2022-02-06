package com.ecom.cartservice.repository;

import com.ecom.cartservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Set<Item> findByCartId(Long cartId);
    @Query("SELECT i from Item i WHERE i.cart.id=?1 and i.productId= ?2" )
    List<Item> findByCartIdandProductId(Long cartId, Long productId);
}
