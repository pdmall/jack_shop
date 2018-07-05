package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.ShopType;
import com.pdkj.jack_shop.model.ShopTypeRel;
import com.pdkj.jack_shop.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class ShopTypeService extends BaseService<ShopType> {

    public List<Map<String, Object>> getAllShopType() {
        List<Map<String, Object>> shop = shopTypeDao.getAllShopType();
        return shop;
    }

    public Long addShopTypeRel(ShopTypeRel shopTypeRel) {
        return shopTypeDao.addShopTypeRel(shopTypeRel);
    }
}
