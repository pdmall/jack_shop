package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.ShopMapper;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.service.ShopService;
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
public class ShopServiceImpl extends AbstractService<Shop> implements ShopService {
    @Resource
    private ShopMapper shopMapper;

    public List<Shop> findByCondition(Map<String,Object> map){
        return shopMapper.findByCondition(map);
    }
    public Shop findById(Long id){
        return shopMapper.findById(id);
    }
    public Shop findAddressById(Long id){
        return shopMapper.findAddressById(id);
    }
}
