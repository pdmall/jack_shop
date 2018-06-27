package com.pdkj.jack_shop.service;
import com.pdkj.jack_shop.model.Coupon;
import com.pdkj.jack_shop.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
public interface CouponService extends Service<Coupon> {
    List<Coupon> findByCondition(Map<String,Coupon> map);
    Coupon findById(Long id);
}
