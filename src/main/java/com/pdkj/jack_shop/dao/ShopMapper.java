package com.pdkj.jack_shop.dao;

import com.pdkj.jack_shop.core.Mapper;
import com.pdkj.jack_shop.model.Shop;

import java.util.List;
import java.util.Map;

public interface ShopMapper extends Mapper<Shop> {
    List<Shop> findByCondition(Map<String,Object> map);
    Shop findById(Long id);
    Shop findAddressById(Long id);
}