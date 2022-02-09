package com.ecom.cartservice.service;

import com.ecom.cartservice.DTO.CartDTO;
import com.ecom.cartservice.DTO.ItemDTO;
import com.ecom.cartservice.domain.Item;


public interface CartService {

    public void addItemToCart(CartDTO cart);

    public CartDTO getCart(Long cartId);

    public void changeItemQuantity(Long cartId, Long productId, Integer quantity);

    public void deleteItemFromCart(Long cartId, Long productId);

    public boolean checkIfItemIsExist(Long cartId, Long productId);

    //public List<Item> getAllItemsFromCart(Long cartId);

    public void deleteCart(Long cartId);

    Item addItemToExistingCart(ItemDTO itemDTO, Long cartId);
}
