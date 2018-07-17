package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.Coupon;
import com.pdkj.jack_shop.model.GroupBuy;
import com.pdkj.jack_shop.model.Goods;
import com.pdkj.jack_shop.model.ShopType;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class CouponService extends BaseService<ShopType> {

    public List<Map<String, Object>> getCouponByShopId(Long shop_id , Integer coupon_state ,Pager pager) {
        List<Map<String, Object>> list = couponDao.getCouponByShopId(shop_id,coupon_state,pager);
        for (Map<String,Object> map:list) {
            map.putAll(couponDao.getSales(Long.parseLong(map.get("id").toString())));
        }
        return list;
    }

    public Map<String, Object> getCouponById(Long id) {
        Map<String, Object> map = couponDao.getCouponById(id);
        return map;
    }

    public List<Map<String, Object>> getCouponByUserId(Long user_id, Integer is_use,Pager pager) {
        List<Map<String, Object>> list = couponDao.getCouponByUserId(user_id,is_use,pager);
        return list;
    }
    public Long addCoupon(Coupon coupon) {
        return couponDao.addCoupon(coupon);
    }


}
