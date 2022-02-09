package com.ecom.cartservice.controller;

import com.ecom.cartservice.DTO.CartDTO;
import com.ecom.cartservice.DTO.ItemDTO;
import com.ecom.cartservice.domain.Cart;
import com.ecom.cartservice.domain.Item;
import com.ecom.cartservice.header.HeaderGenerator;
import com.ecom.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping(value = "/get/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {
        CartDTO cart = cartService.getCart(cartId);
        if (cart != null) {
            return new ResponseEntity<CartDTO>(
                    cart,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<CartDTO>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/addItems")
    public ResponseEntity<Cart> addItemToCart(@RequestBody CartDTO cartDto) {
        {
            CartDTO cartDTO = null;
            Cart cart = null;
            if (cartDto.getCrtId() != null) {
                cartDTO = cartService.getCart(cartDto.getCrtId());
                if (cartDTO != null) {
                    if (!cartDTO.getItemDTOList().isEmpty()) {
                        for (ItemDTO itemDTO : cartDto.getItemDTOList()) {
                            if (cartService.checkIfItemIsExist(cartDto.getCrtId(), itemDTO.getProductId())) {
                                cartService.changeItemQuantity(cartDto.getCrtId(), itemDTO.getProductId(), itemDTO.getQuantity());
                            } else {
                                // add the item to that cart
                                Item item = cartService.addItemToExistingCart(itemDTO, cartDto.getCrtId());
                            }
                        }
                        return new ResponseEntity<Cart>(
                                cart,
                                HttpStatus.CREATED);
                    }
                }

            } else {

                cartService.addItemToCart(cartDto);
                return new ResponseEntity<Cart>(
                        cart,
                        HttpStatus.CREATED);
            }
            return new ResponseEntity<Cart>(
                    headerGenerator.getHeadersForError(),
                    HttpStatus.BAD_REQUEST);
        }
    }



        @DeleteMapping(value = "/removeItems", params = {"productId", "cartId"})
    public ResponseEntity<Void> removeItemFromCart(
            @RequestParam("productId") Long productId,
            @RequestParam("cartId") Long cartId) {
        CartDTO cart = cartService.getCart(cartId);
        if (cart != null) {
            cartService.deleteItemFromCart(cartId, productId);
            return new ResponseEntity<Void>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<Void>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<Void>(headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
    }
}
