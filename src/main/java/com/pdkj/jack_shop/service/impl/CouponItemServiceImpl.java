package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.CouponItemMapper;
import com.pdkj.jack_shop.model.CouponItem;
import com.pdkj.jack_shop.service.CouponItemService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class CouponItemServiceImpl extends AbstractService<CouponItem> implements CouponItemService {
    @Resource
    private CouponItemMapper couponItemMapper;
    public void getTest(){

    }

}
