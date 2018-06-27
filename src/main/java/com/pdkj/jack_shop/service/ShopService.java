package com.pdkj.jack_shop.service;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
public interface ShopService extends Service<Shop> {
    Shop findAddressById(Long id);
    List<Shop> findByClassify(Long id);
}
