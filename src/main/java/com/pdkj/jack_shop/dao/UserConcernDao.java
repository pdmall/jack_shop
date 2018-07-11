package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.UserConcern;
import com.pdkj.jack_shop.util.sql.MySql;
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
public class UserConcernDao extends DaoBase<UserConcern> {

    public List<Map<String, Object>> getUserConcernList(Long shop_id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("uc.created,u.id,u.nickname,u.icon");
        sql.append("FROM");
        sql.append("user_concern AS uc , `user` AS u");
        sql.append("WHERE");
        sql.append("uc.be_user_id = u.id AND uc.shop_id = ?",shop_id);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
    public Map<String, Object> getUserConcernCount(Long shop_id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("count(*) count");
        sql.append("FROM");
        sql.append("user_concern AS uc , `user` AS u");
        sql.append("WHERE");
        sql.append("uc.be_user_id = u.id AND uc.shop_id = ?",shop_id);
        return jdbcTemplate.queryForMap(sql.toString(),sql.getValues());
    }
}