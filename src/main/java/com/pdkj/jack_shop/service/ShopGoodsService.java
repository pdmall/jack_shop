package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.ShopGoods;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class ShopGoodsService extends BaseService<ShopGoods> {

    public List<Map<String, Object>> getCouponGoods(Long coupon_id) {
        List<Map<String, Object>> shop = shopGoodsDao.getCouponGoods(coupon_id);
        return shop;
    }

}
