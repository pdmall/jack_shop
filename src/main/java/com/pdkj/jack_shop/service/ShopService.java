package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.*;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class ShopService extends BaseService<Shop> {

    public List<Map<String, Object>> getShopList(Pager page) {
        List<Map<String, Object>> shop = shopDao.getShopList(page);
        for (Map<String, Object> map : shop) {
            map.put("coupons", couponDao.getCouponByShopId(Long.valueOf(map.get("id").toString()), 1, page));
            map.put("groupBuys", groupBuyDao.getGroupBuyByShopId(Long.valueOf(map.get("id").toString()), 1, page));
            map.put("searchKey", searchKeyDao.getShopSearchKey(map.get("id")));
        }
        return shop;
    }

    @Transactional
    public Long addShop(IsPassShop shop, String[] items, Long type_id, Long user_id) {
        Long shopId = shopDao.addShop(shop);
        for (String item : items) {
            Object o = searchKeyDao.addSearchKey(item);
            searchKeyDao.addSearchKeyRel(shopId, o);
        }
        Object o = searchKeyDao.addSearchKey(shop.getShop_name());
        searchKeyDao.addSearchKeyRel(shopId, o);
        UserShopRel userShopRel = new UserShopRel();
        userShopRel.setId(Tools.generatorId());
        userShopRel.setMaster(1);
        userShopRel.setUser_id(user_id);
        userShopRel.setEmployee_role_id(1);
        userShopRel.setShop_id(shopId);
        userShopRel.setUser_name("拥有者");
        shopDao.addUserShopRel(userShopRel);
        shopWalletDao.save(shopId);
        ;
        shopTypeDao.addShopTypeRel(new ShopTypeRel(shopId, type_id));
        return shopId;
    }

    //获取商铺信息
    public Map<String, Object> getShop(Long id) {
        Map<String, Object> stringObjectMap = shopDao.getShop(id);
        stringObjectMap.put("searchKey", searchKeyDao.getShopSearchKey(id));
        stringObjectMap.put("groupBuys", groupBuyDao.getGroupBuyByShopId(id, 1, new Pager()));
        stringObjectMap.put("coupons", couponDao.getCouponByShopId(id, 1, new Pager()));
        return stringObjectMap;
    }

    //根据商铺ID查询商铺位置
    public Map<String, Object> findAddressById(Long id) {
        return shopDao.findAddressById(id);
    }

    //根据类型分类
    public List<Map<String, Object>> findByClassify(Long type_id, Pager pager) {
        List<Map<String, Object>> shop = shopDao.findByClassify(type_id, pager);
        for (Map<String, Object> map : shop) {
            map.put("coupons", couponDao.getCouponByShopId(Long.valueOf(map.get("id").toString()), 1, pager));
            map.put("groupBuys", groupBuyDao.getGroupBuyByShopId(Long.valueOf(map.get("id").toString()), 1, pager));
            map.put("searchKey", searchKeyDao.getShopSearchKey(map.get("id")));
        }
        return shop;
    }

    //搜索框搜索的内容
    public List<Map<String, Object>> searchBox(Long key, Pager pager) {
        List<Map<String, Object>> shop = shopDao.searchBox(key, pager);
        for (Map<String, Object> map : shop) {
            map.put("coupons", couponDao.getCouponByShopId(Long.valueOf(map.get("id").toString()), 1, pager));
            map.put("groupBuys", groupBuyDao.getGroupBuyByShopId(Long.valueOf(map.get("id").toString()), 1, pager));
            map.put("searchKey", searchKeyDao.getShopSearchKey(map.get("id")));
        }
        return shop;
    }

    //按评分排序
    public List<Map<String, Object>> shopSort(String name, Long type_id, String county, Pager pager) {
        return shopDao.shopSort(name, 2L, "温江区", pager);
    }

    //按距离排序
    public List<Map<String, Object>> shopDistanceSort(String name, Long type_id, String county, Pager pager, String latitude, String longitude) {
        return shopDao.shopDistanceSort(name, type_id, county, pager, latitude, longitude);
    }

    //获得多少距离以内的商铺
    public List<Map<String, Object>> shopDistanceValueSort(String name, Long type_id, String county, Pager pager, String latitude, String longitude, int distance) {
        return shopDao.shopDistanceValueSort(name, type_id, county, pager, latitude, longitude, distance);
    }

    //按用餐时段查询
    public List<Map<String, Object>> shopMealTime(Long mealTimeId, String county, Pager pager) {
        return shopDao.shopMealTime(county, pager, mealTimeId);
    }

    //搜索框 提示 字典检索
    public List<Map<String, Object>> getSearchKey(String name, Pager pager) {
        return shopDao.getSearchKey(name, pager);
    }

    //获得用户的商铺审核日志
    public List<Map<String, Object>> getShopPassFinish(Long id, Pager pager) {
        return shopDao.getShopPassFinish(id, pager);
    }


}
