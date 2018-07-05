package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.dao.CouponDao;
import com.pdkj.jack_shop.model.Coupon;
import com.pdkj.jack_shop.model.ShopType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class CouponService extends BaseService<ShopType> {

    public List<Map<String, Object>> getCouponByShopId(Long shopId) {
        List<Map<String, Object>> list = couponDao.getControllerByShopId(shopId);
        return list;
    }

    public Map<String, Object> getCouponById(Long id) {
        Map<String, Object> map = couponDao.getControllerById(id);
        return map;
    }
    public List<Map<String, Object>> getCouponByUserId(Long userId) {
        List<Map<String, Object>> list = couponDao.getControllerByUserId(userId);
        return list;
    }
    public Long addCoupon(Coupon coupon) {
        return couponDao.addCoupon(coupon);
    }
}
