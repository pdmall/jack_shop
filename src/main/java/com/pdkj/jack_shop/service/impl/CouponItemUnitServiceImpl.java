package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.CouponItemUnitMapper;
import com.pdkj.jack_shop.model.CouponItemUnit;
import com.pdkj.jack_shop.service.CouponItemUnitService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class CouponItemUnitServiceImpl extends AbstractService<CouponItemUnit> implements CouponItemUnitService {
    @Resource
    private CouponItemUnitMapper couponItemUnitMapper;

}
