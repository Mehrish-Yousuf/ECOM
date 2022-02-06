package com.ecom.cartservice.DTO;

import java.util.Set;

public class CartDTO {

    private Long crtId;
    private Set<ItemDTO> itemDTOList;

    public Long getCrtId() {
        return crtId;
    }

    public void setCrtId(Long crtId) {
        this.crtId = crtId;
    }

    public Set<ItemDTO> getItemDTOList() {
        return itemDTOList;
    }

    public void setItemDTOList(Set<ItemDTO> itemDTOList) {
        this.itemDTOList = itemDTOList;
    }
}
