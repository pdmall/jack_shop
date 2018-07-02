package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 17:37
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.ShopType;
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
    public List<Map<String, Object>> getControllerByShopId(Long shopId) {
        String sql = " select " +
                " id,title,`type`,discount,buy_price, " +
                " final_price,is_refund,sub_time," +
                " date_start,date_end,time_start,time_end, " +
                " unable_date,coupon_img " +
                " from coupon " +
                " where shop_id = ? ";
        return jdbcTemplate.queryForList(sql, shopId);
    }
    public Map<String, Object> getControllerById(Long id) {
        String sql = " select " +
                " id,title,`type`,discount,buy_price, " +
                " final_price,is_refund,sub_time,`describe`, " +
                " date_start,date_end,time_start,time_end, " +
                " unable_date,coupon_img " +
                " from coupon " +
                " where id = ? ";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public List<Map<String, Object>> getControllerByUserId(Long UserId) {
        String sql = " select " +
                " coupon.id,title,`type`,discount,buy_price, " +
                " final_price,is_refund,sub_time, " +
                " date_start,date_end,time_start,time_end, " +
                " unable_date,coupon_img " +
                " from coupon inner join user_coupon_rel on coupon.id = user_coupon_rel.coupon_id" +
                " where user_coupon_rel.user_id = ? ";
        return jdbcTemplate.queryForList(sql, UserId);
    }

}
