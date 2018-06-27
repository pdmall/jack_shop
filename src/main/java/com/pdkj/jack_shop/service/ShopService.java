package com.pdkj.jack_shop.service;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
public interface ShopService extends Service<Shop> {

    public List<Shop> getShop(String name);

}
