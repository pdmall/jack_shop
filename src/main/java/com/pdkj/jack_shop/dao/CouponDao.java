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
    public List<Map<String, Object>> getCouponByShopId(Long shopId,Integer coupon_state) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("c.title,c.type,c.buy_price,c.final_price,c.appointment,c.stock_count,c.once_count,");
        sql.append("`range_name`,c.id,`type_name`,unavailable_date,count(use_coupon_id) sale_volume");
        sql.append("FROM");
        sql.append("coupon c ,coupon_goods_range cgr ,");
        sql.append("coupon_type ct ,user_order ");
        sql.append("WHERE");
        sql.append("goods_range_id = cgr.id AND c.id = user_order.use_coupon_id AND");
        sql.append("ct.id = c.type AND c.shop_id = ? AND coupon_state = ?",shopId,coupon_state);
        sql.append("GROUP BY use_coupon_id");
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    public Map<String, Object> getCouponById(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("c.id,c.title,c.type,c.buy_price,c.final_price,c.appointment,c.`describe`,c.date_start,");
        sql.append(" c.date_end,c.time_start,c.time_end,c.coupon_img,c.shop_id,c.buy_person_limit,");
        sql.append(" c.stock_count,c.once_count,diners_number,c.unavailable_date,cgr.range_name,ct.type_name");
        sql.append("FROM");
        sql.append("coupon c ,coupon_goods_range cgr ,");
        sql.append("coupon_type ct");
        sql.append("WHERE");
        sql.append("goods_range_id = cgr.id AND");
        sql.append("ct.id = c.type AND c.id = ? ",id);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getCouponByUserId(Long UserId,Integer coupon_state) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("c.title,c.type,c.buy_price,c.final_price,c.appointment,c.stock_count,");
        sql.append("c.once_count,`range_name`,c.id,`type_name`,unavailable_date");
        sql.append("FROM");
        sql.append("coupon c ,coupon_goods_range cgr ,");
        sql.append("coupon_type ct,user_coupon_rel ucr)");
        sql.append("WHERE");
        sql.append("goods_range_id = cgr.id AND ucr.coupon_id = c.id AND");
        sql.append("ct.id = c.type AND user_id = ? AND coupon_state = ?",UserId,coupon_state);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    public int couponSaleVolume(Long coupon_id){
        String sql = "SELECT count(id) FROM `user_order` WHERE use_coupon_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{coupon_id}, Integer.class);
    }

    /**
     * @Title:
     * @Description: 添加卷
     * @author lvchong
     * @params * @param null
     * @date 2018-07-07
     * @throw YnCorpSysException
     */
    public Long addCoupon(IsPassCoupon coupon){
        coupon.setId(Tools.generatorId());
        SqlInfo insertSQL = SQLTools.getInsertSQL(coupon,"is_pass_coupon");
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
        return coupon.getId();
    }

}
