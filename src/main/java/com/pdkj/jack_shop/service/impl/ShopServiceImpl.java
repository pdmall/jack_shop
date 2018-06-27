package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.ShopMapper;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.service.ShopService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class ShopServiceImpl extends AbstractService<Shop> implements ShopService {
    @Resource
    private ShopMapper shopMapper;

    public Shop findAddressById(Long id){
        return shopMapper.findAddressById(id);
    }
}
