package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class ShopService extends BaseService<Shop> {

    public List<Map<String,Object>> getShopList(Pager page) {
        List<Map<String, Object>> shop = shopDao.getShopList(page);
        for (Map<String,Object> map : shop){
            map.put("coupons",couponDao.getControllerByShopId(Long.valueOf(map.get("id").toString())));
        }
        return shop;
    }


    public Long addShop(Shop shop){
        return shopDao.addShop(shop);
    }
    public Map<String, Object> getShop(Long id){
        return shopDao.getShop(id);
    }
    public Map<String, Object> findAddressById(Long id){
        return shopDao.findAddressById(id);
    }
    public List<Map<String, Object>> findByClassify(Long type_id,Pager pager) {
        return shopDao.findByClassify(type_id,pager);
    }
    public List<Map<String, Object>> searchBox(String name ,String county,Pager pager){
        return shopDao.searchBox(name,county,pager);
    }


}
