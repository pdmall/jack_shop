package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.Goods;
import com.pdkj.jack_shop.model.IsPassShop;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.model.UserShopRel;
import com.pdkj.jack_shop.util.Tools;
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
            map.put("coupons",couponDao.getCouponByShopId(Long.valueOf(map.get("id").toString()),1));
        }
        return shop;
    }


    public Long addShop(IsPassShop shop){
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

    public List<Map<String, Object>> shopSort(String name,Long type_id, String county, Pager pager){
        return shopDao.shopSort(name,2L,"温江区",pager);
    }
    public List<Map<String, Object>> shopDistanceSort(String name,Long type_id, String county, Pager pager,String latitude,String longitude){
        return shopDao.shopDistanceSort( name, type_id,  county,  pager, latitude, longitude);
    }
    public List<Map<String, Object>> shopDistanceValueSort(String name,Long type_id, String county, Pager pager,String latitude,String longitude ,int distance){
        return shopDao.shopDistanceValueSort( name, type_id,  county,  pager, latitude, longitude,distance);
    }
    public List<Map<String, Object>> shopMealTime(Long mealTimeId, String county, Pager pager){
        return shopDao.shopMealTime( county,pager, mealTimeId);
    }

    public List<Map<String, Object>> getShopName(String name , Pager pager ){
        return shopDao.getShopName( name,pager);
    }

    public List<Map<String, Object>> getShopPassFinish(Long id){
        return shopDao.getShopPassFinish(id);
    }

    //添加属于哪个商铺的菜品
    public void addShopGoods(Goods goods,Long shop_id){
        shopDao.addShopGoodsRel(shop_id,goodsDao.addGoods(goods));
    }
    //查询用户的商铺
    public List<Map<String, Object>> getMyShopList(Long user_id) {
        return shopDao.getMyShopList(user_id);
    }
    //获得商铺的店员
    public List<Map<String, Object>> getEmployee(Long shop_id) {
        return shopDao.getEmployee(shop_id);
    }

    public  List<Map<String, Object>> getEmployeeRole() {
        return shopDao.getEmployeeRole();
    }

    public void updateEmployeeRole(UserShopRel userShopRel) {
        shopDao.updateEmployeeRole(userShopRel);
    }

    public void addEmployeeRole(UserShopRel userShopRel) {
        userShopRel.setId(Tools.generatorId());
        shopDao.addEmployeeRole(userShopRel);
    }

    public void delEmployeeRole(Long id) {
        shopDao.delEmployeeRole(id);
    }
}
