package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserOrderService extends BaseService<UserOrder> {

    public Long addOrder(Long shop_id,Long user_id) {
        return userOrderDao.addOrder(shop_id,user_id);
    }
    public Long updateOrder(UserOrder userOrder){
        return userOrderDao.updateOrder(userOrder);
    }
    public List<Map<String, Object>> getUserOrder(Long user_id , Pager page){
        return userOrderDao.getUserOrder(user_id,page);
    }

    public List<Map<String, Object>> getShopOrder(Long shop_id , Pager page){
        return userOrderDao.getShopOrder(shop_id,page);
    }

}
