package com.ecom.cartservice.service;

import com.ecom.cartservice.DTO.CartDTO;
import com.ecom.cartservice.DTO.ItemDTO;
import com.ecom.cartservice.DTO.ProductDTO;
import com.ecom.cartservice.domain.Cart;
import com.ecom.cartservice.domain.Item;
import com.ecom.cartservice.feignclient.ProductClient;
import com.ecom.cartservice.repository.CartRepository;
import com.ecom.cartservice.repository.ItemRepository;
import com.ecom.cartservice.utilities.CartUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service

public class CartServiceImpl implements CartService {

    @Autowired
    private ProductClient productClient;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addItemToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setStatus("INITIATED");
        cartRepository.save(cart);

        Set<Item> items = new HashSet<>();

        for (ItemDTO itemDTO : cartDTO.getItemDTOList()){
            ProductDTO productDTO = getProductDetails(itemDTO.getProductId());
            if(productDTO!=null){
                Item item = new Item();
                item.setProductId(productDTO.getId());
                item.setQuantity(itemDTO.getQuantity());
                item.setSubTotal(CartUtilities.getSubTotalForItem(productDTO.getPrice(), itemDTO.getQuantity()));
                item.setCart(cart);
                itemRepository.save(item);
                items.add(item);
            }

        }


    }

    @Override
    public CartDTO getCart(Long cartId) {
        CartDTO cartDTO = new CartDTO();
        Set<ItemDTO> itemDTOSet = new HashSet<>();
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        Cart cart = cartOpt.get();

        if (cart != null) {
            Set<Item> itemsSet = itemRepository.findByCartId(cart.getId());
            for(Item item : itemsSet){
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setCrtId(item.getCart().getId());
                itemDTO.setId(item.getId());
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setProductId(item.getProductId());
                itemDTO.setSubTotal(item.getSubTotal());
                itemDTOSet.add(itemDTO);

            }

            cartDTO.setItemDTOList(itemDTOSet);

        }
        return cartDTO;
    }

    @Override
    public void changeItemQuantity(Long cartId, Long productId, Integer quantity) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        Cart cart =cartOpt.get();
        CartDTO cartDTO = new CartDTO();
        for (Item item : cart.getItems()) {
            if ((item.getProductId()).equals(productId)) {
                ProductDTO productDTO = getProductDetails(productId);
                deleteItemFromCart(cartId, productId);
                item.setQuantity(quantity);
                item.setSubTotal(CartUtilities.getSubTotalForItem(productDTO.getPrice(), quantity));
                item.setCart(cart);
                itemRepository.save(item);
            }
        }
    }

    @Override
    public void deleteItemFromCart(Long cartId, Long productId) {
        Set<Item> items = itemRepository.findByCartId(cartId);
        for (Item item : items) {
            if (item.getProductId().equals(productId)) {
                itemRepository.delete(item);
            }
        }
    }



    @Override
    public boolean checkIfItemIsExist(Long cartId, Long productId) {
        Item item = (Item) itemRepository.findByCartIdandProductId(cartId, productId);
        if ((item.getProductId()).equals(productId)) {
                return true;

        }
        return false;

    }

//    @Override
//    public List<Item> getAllItemsFromCart(String cartId) {
//        List<Item> items = (List) cartRedisRepository.getCart(cartId, Item.class);
//        return items;
//    }
//
    @Override
    public void deleteCart(Long cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        Cart cart = cartOpt.get();
        if(cart!=null){
            Set<Item> items = itemRepository.findByCartId(cartId);
            for(Item item : items){
                itemRepository.delete(item);
            }
            cartRepository.delete(cart);
        }


    }

    ProductDTO getProductDetails(Long productId){
        String productUrl = "http://localhost:4000/catalog/productById/{id}";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity<>(httpHeaders);
        Map<String, Long> params = new HashMap<>();
        params.put("id", productId);

        ResponseEntity<ProductDTO> response = restTemplate.exchange(productUrl, HttpMethod.GET, entity, ProductDTO.class, params);
        System.out.println(response.getBody());
        return response.getBody();
    }
}
