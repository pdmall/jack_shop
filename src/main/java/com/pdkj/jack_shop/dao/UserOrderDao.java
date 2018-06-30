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
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Repository;

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

    public List getShopOrder(){
        String sql = " select id, ";
        return null;
    }
    public List getUserOrder(){

        return null;
    }
}
