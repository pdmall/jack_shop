package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.UserOrderMapper;
import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.service.UserOrderService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class UserOrderServiceImpl extends AbstractService<UserOrder> implements UserOrderService {
    @Resource
    private UserOrderMapper userOrderMapper;

}
