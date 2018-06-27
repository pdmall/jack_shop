package com.pdkj.jack_shop.dao;

import com.pdkj.jack_shop.core.Mapper;
import com.pdkj.jack_shop.model.Shop;

import java.util.List;

public interface ShopMapper extends Mapper<Shop> {

    public List<Shop> getShop(String name);

}