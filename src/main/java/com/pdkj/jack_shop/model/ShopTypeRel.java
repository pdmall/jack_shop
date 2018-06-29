package com.pdkj.jack_shop.model;

import javax.persistence.*;

@Table(name = "shop_type_rel")
public class ShopTypeRel {
    @Id
    private Long id;

    private Long shop_id;

    private Long type_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShop_id() {
        return shop_id;
    }

    public void setShop_id(Long shop_id) {
        this.shop_id = shop_id;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }
}