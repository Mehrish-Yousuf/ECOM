package com.ecom.cartservice.utilities;

import java.math.BigDecimal;

public class CartUtilities {

    public static BigDecimal getSubTotalForItem(BigDecimal price , int quantity) {
        return (price).multiply( BigDecimal.valueOf(quantity));
    }
}
