package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/7 10:45
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.util.sql.MySql;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName ShopCommentDao
 * @Description 类描述
 * @date 2018/7/7
 */
@Repository
public class ShopCommentDao extends DaoBase<Banner> {
    public List<Map<String,Object>> getCommentList(Long shop_id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("id,user_id,shop_id,nickname,user_img,`comment`,taste_score,");
        sql.append("enviro_score,service_score,comment_img,created,shop_reply,order_id");
        sql.append("FROM");
        sql.append("shop_comment where shop_id = ?",shop_id);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
}
