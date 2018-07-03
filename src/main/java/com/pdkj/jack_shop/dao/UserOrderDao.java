package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.Pager;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName ShopDao
 * @Description 类描述
 * @date 2018/6/28
 */
@Repository
public class UserOrderDao extends DaoBase<Banner> {

    public Long addOrder(Long shop_id, Long user_id) {
        String sql = "insert into user_Order(id,user_id,shop_id) values (?,?,?)";
        Long id = Tools.generatorId();
        jdbcTemplate.update(sql,id , user_id, shop_id);
        return id;
    }

    public Long updateOrder(UserOrder userOrder){
        SqlInfo insertSQL =SQLTools.getUpdateById(userOrder,"user_order",userOrder.getId());
        jdbcTemplate.update(insertSQL.getSql(),insertSQL.getValues());
        return userOrder.getId();
    }

    public List<Map<String, Object>> getShopOrder(Long shop_id,Pager page){
        MySql sql = new MySql();
        sql.append("select id,pay_on,item_id,state,pay_type,user_id,created,buy_time,");
        sql.append("use_time,use_coupon_id,wallet_money,total_price,final_price,shop_id ");
        sql.append(" FROM user_order ");
        sql.append(" where shop_id = ?",shop_id);
        sql.limit(page);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
    public List<Map<String, Object>> getUserOrder(Long user_id,Pager page){
        MySql sql = new MySql();
        sql.append("select id,pay_on,item_id,state,pay_type,user_id,created,buy_time,");
        sql.append("use_time,use_coupon_id,wallet_money,total_price,final_price,shop_id ");
        sql.append(" FROM user_order ");
        sql.append(" where user_id = ?",user_id);
        sql.limit(page);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }

    public String getOrderPrice(String order_id) {
        String sql = "select final_price from user_order where id =  ? and state = 0";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, order_id);
        if(maps.size()>0){
            return maps.get(0).get("final_price").toString();
        }
        return null;
    }

    public void paySuccess(String orderId) {
        String sql = "update user_order set state = 1 where id = ? and state = 0";
        jdbcTemplate.update(sql,orderId);
    }
}
