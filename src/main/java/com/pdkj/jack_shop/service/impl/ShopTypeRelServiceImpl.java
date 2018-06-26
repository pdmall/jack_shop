package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.ShopTypeRelMapper;
import com.pdkj.jack_shop.model.ShopTypeRel;
import com.pdkj.jack_shop.service.ShopTypeRelService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class ShopTypeRelServiceImpl extends AbstractService<ShopTypeRel> implements ShopTypeRelService {
    @Resource
    private ShopTypeRelMapper shopTypeRelMapper;

}
