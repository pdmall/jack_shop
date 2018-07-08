package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.ShopGoods;
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
public class ShopGoodsDao extends DaoBase<ShopGoods> {

    public List<Map<String, Object>> getCouponGoods(Long coupon_id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("sgu.`name` unit_name,sg.id,sg.`name`,sg.price,sg.img_url,sg.`describe`");
        sql.append("FROM");
        sql.append("shop_goods AS sg ,shop_goods_unit AS sgu ,coupon_goods_rel cgr");
        sql.append("WHERE");
        sql.append("sg.unit_id = sgu.id AND sg.id = cgr.goods_id AND cgr.coupon_id=?",coupon_id);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
}
