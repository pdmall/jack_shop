package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.CouponMapper;
import com.pdkj.jack_shop.model.Coupon;
import com.pdkj.jack_shop.service.CouponService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class CouponServiceImpl extends AbstractService<Coupon> implements CouponService {
    @Resource
    private CouponMapper couponMapper;

    public List<Coupon> findByCondition(Map<String,Coupon> map){
        return couponMapper.findByCondition(map);
    }
    public Coupon findById(Long id){
        return couponMapper.findById(id);
    }
}
