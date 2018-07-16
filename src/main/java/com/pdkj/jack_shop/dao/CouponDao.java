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
import com.pdkj.jack_shop.model.GroupBuy;
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
        sql.append("SELECT ");
        sql.append("c.id,c.type,c.original_price,c.buy_price,c.appointment,c.date_start,c.buy_person_limit, ");
        sql.append("c.date_end,c.time_start,c.time_end,c.shop_id,c.unavailable_date,c.goods_range_id, ");
        sql.append("c.stock_count,c.once_count,cgr.range_name,count(uo.coupon_id) sale_volume ,s.home_img");
        sql.append("FROM ");
        sql.append("coupon AS c ,coupon_goods_range AS cgr ,user_order uo , shop s");
        sql.append("WHERE ");
        sql.append(" c.goods_range_id = cgr.id and c.shop_id = s.id AND uo.shop_id = c.shop_id AND ");
        sql.append("coupon_state = ? AND c.shop_id = ? ",coupon_state,shopId);
        sql.append("GROUP BY uo.coupon_id");
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public Map<String, Object> getCouponById(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("c.id,c.type,c.original_price,c.buy_price,c.appointment,c.date_start, ");
        sql.append("c.date_end,c.time_start,c.time_end,c.shop_id,c.unavailable_date,c.goods_range_id, ");
        sql.append("c.buy_person_limit,c.stock_count,c.once_count,cgr.range_name ");
        sql.append("FROM ");
        sql.append("coupon AS c ,coupon_goods_range AS cgr ");
        sql.append("WHERE ");
        sql.append("coupon_state = 1 AND c.goods_range_id = cgr.id AND c.id = ? ",id);

        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getCouponByUserId(Long UserId,Integer coupon_state) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("c.id,c.type,c.original_price,c.buy_price,c.appointment,c.date_start,c.buy_person_limit, ");
        sql.append("c.date_end,c.time_start,c.time_end,c.shop_id,c.unavailable_date,c.goods_range_id, ");
        sql.append("c.stock_count,c.once_count,cgr.range_name ");
        sql.append("FROM ");
        sql.append("coupon AS c ,coupon_goods_range AS cgr ,user_coupon_rel ucr");
        sql.append("WHERE ");
        sql.append(" ucr.coupon_id = c.id AND c.goods_range_id = cgr.id AND ");
        sql.append("coupon_state = ? AND ucr.user_id = ? ",coupon_state,UserId);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    /**
     * @Title:
     * @Description: 添加卷
     * @author lvchong
     * @params * @param null
     * @date 2018-07-07
     * @throw YnCorpSysException
     */
    public Long addCoupon(Coupon coupon){
        coupon.setId(Tools.generatorId());
        SqlInfo insertSQL = SQLTools.getInsertSQL(coupon,"coupon");
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
        return coupon.getId();
    }

}
