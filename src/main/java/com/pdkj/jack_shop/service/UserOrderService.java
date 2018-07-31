package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.model.UserOrderDetails;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserOrderService extends BaseService<UserOrder> {

    public Long addOrder(UserOrder userOrder, UserOrderDetails userOrderDetails, Long user_id) {
        userOrder.setUser_id(user_id);
        Long id = userOrderDao.addOrder(userOrder);
        for (int i = 0; i < userOrder.getQuantity(); i++) {
            userOrderDetails.setUser_order_id(id);
            userOrderDao.addOrderDetails(userOrderDetails);
        }
        return id;
    }

    public Long updateOrder(UserOrder userOrder) {
        return userOrderDao.updateOrder(userOrder);
    }

    public List<Map<String, Object>> getUserOrder(Long user_id, Integer order_state_id, Pager page) {
        return userOrderDao.getUserOrder(user_id, order_state_id, page);
    }

    public List<Map<String, Object>> getShopOrder(Long shop_id, Pager page) {
        return userOrderDao.getShopOrder(shop_id, page);
    }

    public List<Map<String, Object>> getDetailsQR(Long order_id) {
        return userOrderDao.getDetailsQR(order_id);
    }

    public Map<String, Object> userOrderDetails(String order_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_order",userOrderDao.userOrderDetails(order_id));
        if(Integer.valueOf(map.get("type_of_id").toString())==1){
            map.put("item",couponDao.getCoupon(map.get("item_id")));
        }else if(Integer.valueOf(map.get("type_of_id").toString())==2){
            map.put("item",groupBuyDao.getGroupBuy(map.get("item_id")));
        }else if(Integer.valueOf(map.get("type_of_id").toString())==4){
            map.put("item",userDao.getRoleById(map.get("item_id")));
        }else{
            throw new CustomException("类型不对哟");
        }
        return map;
    }

    public List<Map<String, Object>> getQRState(Long order_id){
        return userOrderDao.getQRState(order_id);
    }
}
