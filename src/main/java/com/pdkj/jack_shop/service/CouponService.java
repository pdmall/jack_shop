package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.dao.CouponDao;
import com.pdkj.jack_shop.model.ShopType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class CouponService extends BaseService<ShopType> {

    public List<Map<String, Object>> getControllerByShopId(Long shopId) {
        List<Map<String, Object>> list = couponDao.getControllerByShopId(shopId);
        return list;
    }

}
