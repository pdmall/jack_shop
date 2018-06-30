package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
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

}
