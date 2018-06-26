package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.UserShopRelMapper;
import com.pdkj.jack_shop.model.UserShopRel;
import com.pdkj.jack_shop.service.UserShopRelService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class UserShopRelServiceImpl extends AbstractService<UserShopRel> implements UserShopRelService {
    @Resource
    private UserShopRelMapper userShopRelMapper;

}
