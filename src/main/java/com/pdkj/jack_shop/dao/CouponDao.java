package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 17:37
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.Coupon;
import com.pdkj.jack_shop.model.IsPassCoupon;
import com.pdkj.jack_shop.model.ShopType;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName CouponDao
 * @Description 类描述
 * @date 2018/6/28
 */
@Repository
public class CouponDao extends DaoBase<ShopType> {
    public List<Map<String, Object>> getCouponByShopId(Long shopId) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("c.title,c.type,c.buy_price,c.final_price,c.appointment,c.stock_count,");
        sql.append("c.once_count,`range_name`,c.id,`type_name`,unavailable_date");
        sql.append("FROM");
        sql.append("coupon c ,coupon_goods_range cgr ,");
        sql.append("coupon_type ct");
        sql.append("WHERE");
        sql.append("goods_range_id = cgr.id AND");
        sql.append("ct.id = c.type AND shop_id = ? AND coupon_state = 1",shopId);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    public Map<String, Object> getCouponById(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append(" c.id,c.title,c.type,c.buy_price,c.final_price,c.appointment,c.`describe`,");
        sql.append(" c.date_start,c.date_end,c.time_start,c.time_end,c.coupon_img,c.shop_id,c.buy_person_limit,");
        sql.append("    c.stock_count,c.once_count,c.unavailable_date,cgr.range_name,ct.type_name");
        sql.append("FROM");
        sql.append("coupon c ,coupon_goods_range cgr ,");
        sql.append("coupon_type ct");
        sql.append("WHERE");
        sql.append("goods_range_id = cgr.id AND");
        sql.append("ct.id = c.type AND c.id = ? AND coupon_state = 1",id);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getCouponByUserId(Long UserId) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("c.title,c.type,c.buy_price,c.final_price,c.appointment,c.stock_count,");
        sql.append("c.once_count,`range_name`,c.id,`type_name`,unavailable_date");
        sql.append("FROM");
        sql.append("coupon c ,coupon_goods_range cgr ,");
        sql.append("coupon_type ct,user_coupon_rel ucr)");
        sql.append("WHERE");
        sql.append("goods_range_id = cgr.id AND ucr.coupon_id = c.id AND");
        sql.append("ct.id = c.type AND user_id = ? AND coupon_state = 1",UserId);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public Long addCoupon(IsPassCoupon coupon){
        coupon.setId(Tools.generatorId());
         SqlInfo insertSQL = SQLTools.getInsertSQL(coupon,"is_pass_coupon");
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
        return coupon.getId();
    }

}
