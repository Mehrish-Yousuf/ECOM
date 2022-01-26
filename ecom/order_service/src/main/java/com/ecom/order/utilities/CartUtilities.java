package com.ecom.order.utilities;

import com.ecom.order.domain.Product;

import java.math.BigDecimal;


public class CartUtilities {

    public static BigDecimal getSubTotalForItem(Product product, int quantity) {
        return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}
