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
import com.pdkj.jack_shop.model.UserCouponRel;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.Pager;
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
    public List<Map<String, Object>> getCouponByShopId(Long shop_id, Integer coupon_state, Pager pager) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append(" c.id,c.original_price,c.buy_price,c.appointment,c.date_start,c.date_end,");
        sql.append(" c.time_start,c.time_end,c.created,c.buy_person_limit,c.stock_count,c.once_count,");
        sql.append(" c.unavailable_date,cgr.range_name,c.shop_id,s.shop_name,s.home_img");
        sql.append("FROM ");
        sql.append("coupon AS c, shop s ,coupon_goods_range AS cgr ");
        sql.append("WHERE ");
        sql.append("  c.shop_id = s.id AND c.goods_range_id = cgr.id AND");
        sql.append("coupon_state = ? AND c.shop_id = ? ", coupon_state, shop_id);
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getCouponByUserId(Long user_id,Pager pager) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append(" c.id,c.original_price,c.buy_price,c.appointment,c.date_start,c.date_end,");
        sql.append(" c.time_start,c.time_end,c.created,c.buy_person_limit,c.stock_count,c.once_count,");
        sql.append(" c.unavailable_date,cgr.range_name,c.shop_id,s.shop_name,s.home_img,coupon_state,is_use");
        sql.append("FROM ");
        sql.append("coupon AS c, shop s ,coupon_goods_range AS cgr ,user_coupon_rel ucr");
        sql.append("WHERE ");
        sql.append("  c.shop_id = s.id AND c.goods_range_id = cgr.id AND ucr.coupon_id = c.id AND");
        sql.append(" ucr.user_id = ? ",  user_id);
        sql.append("order by c.coupon_state ,is_use desc, ucr.created desc");
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public Map<String, Object> getCouponById(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append(" c.id,c.original_price,c.buy_price,c.appointment,c.date_start,c.date_end,");
        sql.append(" c.time_start,c.time_end,c.created,c.buy_person_limit,c.stock_count,c.once_count,");
        sql.append(" c.unavailable_date,cgr.range_name,c.shop_id,s.shop_name,s.home_img,s.shop_address,s.shop_phone,s.street");
        sql.append("FROM ");
        sql.append("coupon AS c ,coupon_goods_range AS cgr ,shop s");
        sql.append("WHERE ");
        sql.append("c.shop_id = s.id AND c.goods_range_id = cgr.id AND c.id = ? ", id);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }

    public Map<String, Object> getSales(Long coupon_id) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("count(*) sales");
        sql.append("FROM ");
        sql.append("user_order_details");
        sql.append("WHERE ");
        sql.append("type_of = 2 AND item_id = ? ", coupon_id);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }

    /**
     * @Title:
     * @Description: 添加卷
     * @author lvchong
     * @params * @param null
     * @date 2018-07-07
     * @throw YnCorpSysException
     */
    public Long addCoupon(Coupon coupon) {
        coupon.setId(Tools.generatorId());
        SqlInfo insertSQL = SQLTools.getInsertSQL(coupon, "coupon");
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
        return coupon.getId();
    }

    public void addUserCouponRel(UserCouponRel userCouponRel){
        userCouponRel.setId(Tools.generatorId());
        SqlInfo insertSQL = SQLTools.getInsertSQL(userCouponRel, "user_coupon_rel");
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
    }


}
