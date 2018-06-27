package com.pdkj.jack_shop.dao;

import com.pdkj.jack_shop.core.Mapper;
import com.pdkj.jack_shop.model.Shop;

import java.util.List;
import java.util.Map;

public interface ShopMapper extends Mapper<Shop> {
    Shop findAddressById(Long id);
    List<Shop> findByClassify(Long id);
}