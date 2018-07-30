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
public class CouponDao extends DaoBase {
    public List<Map<String, Object>> getCouponByShopId(Long shop_id, Integer coupon_state, Pager pager) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append(" c.id,c.original_price,c.buy_price,c.appointment,c.date_start,c.date_end,c.type_of_id,");
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
        sql.append(" c.original_price,c.buy_price,c.appointment,c.type_of_id,uod.id QR ,");
        sql.append(" c.unavailable_date,cgr.range_name,s.shop_name,s.home_img,c.coupon_state,uod.order_state_id");
        sql.append("FROM ");
        sql.append("coupon AS c, shop s ,coupon_goods_range AS cgr ,user_order_details uod ,user_order uo");
        sql.append("WHERE ");
        sql.append("  c.shop_id = s.id AND c.goods_range_id = cgr.id AND uod.item_id = c.id AND uod.user_order_id = uo.id AND");
        sql.append(" uo.user_id = ? AND uod.type_of_id = 1",  user_id);
        sql.append("order by c.coupon_state ,uod.order_state_id desc, uo.created desc");
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    public Map<String, Object> getCouponById(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append(" c.id,c.original_price,c.buy_price,c.appointment,c.date_start,c.date_end,c.type_of_id,");
        sql.append(" c.time_start,c.time_end,c.created,c.buy_person_limit,c.stock_count,c.once_count,");
        sql.append(" c.unavailable_date,cgr.range_name,c.shop_id,s.shop_name,s.home_img,s.shop_address,s.shop_phone,s.street");
        sql.append("FROM ");
        sql.append("coupon AS c ,coupon_goods_range AS cgr ,shop s");
        sql.append("WHERE ");
        sql.append("c.shop_id = s.id AND c.goods_range_id = cgr.id AND c.id = ? ", id);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }
    //获得销售总数
    public Map<String, Object> getSales(Long coupon_id) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("count(*) sales");
        sql.append("FROM ");
        sql.append("user_order_details");
        sql.append("WHERE ");
        sql.append("type_of_id = 1 AND item_id = ? ", coupon_id);
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

    public Map<String , Object> verifyCoupon(Long item_id){
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append("original_price,buy_price,date_start,date_end,time_start,time_end");
        mySql.append("from");
        mySql.append("coupon");
        mySql.append("where");
        mySql.append("id = ? AND coupon_state = 1 ",item_id);
        return jdbcTemplate.queryForMap(mySql.toString(),mySql.getValues());
    }

}
