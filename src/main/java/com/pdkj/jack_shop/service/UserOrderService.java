package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.model.UserOrderDetails;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public void paySuccess(String orderId, Date pay_time, Integer trade_type) {
        userOrderDao.paySuccess(orderId, pay_time, trade_type);
    }

    public List<Map<String, Object>> getOrderInfo(String orderId) {
        return userOrderDao.getOrder(orderId);
    }

    public List<Map<String, Object>> getDetailsQR(Long order_id) {
        return userOrderDao.getDetailsQR(order_id);
    }
}
