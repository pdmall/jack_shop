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
        sql.append("c.once_count,`range_name`,c.id,`type_name`");
        sql.append("FROM");
        sql.append("coupon c ,coupon_goods_range cgr ,");
        sql.append("coupon_type ct");
        sql.append("WHERE");
        sql.append("goods_range_id = cgr.id   AND");
        sql.append("ct.id = c.type AND shop_id = ? AND coupon_state = 1",shopId);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    public Map<String, Object> getCouponById(Long id) {
        String sql = " select " +
                " id,title,`type`,discount,buy_price, " +
                " final_price,is_refund,sub_time,`describe`, " +
                " date_start,date_end,time_start,time_end, " +
                " unable_date,coupon_img " +
                " from coupon " +
                " where id = ? ";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public List<Map<String, Object>> getCouponByUserId(Long UserId) {
        String sql = " select " +
                " coupon.id,title,`type`,discount,buy_price, " +
                " final_price,is_refund,sub_time, " +
                " date_start,date_end,time_start,time_end, " +
                " unable_date,coupon_img " +
                " from coupon inner join user_coupon_rel on coupon.id = user_coupon_rel.coupon_id" +
                " where user_coupon_rel.user_id = ? and user_coupon_rel.is_use = 1";
        return jdbcTemplate.queryForList(sql, UserId);
    }

    public Long addCoupon(Coupon coupon){
        coupon.setId(Tools.generatorId());
         SqlInfo insertSQL = SQLTools.getInsertSQL(coupon);
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
        return coupon.getId();
    }

}
