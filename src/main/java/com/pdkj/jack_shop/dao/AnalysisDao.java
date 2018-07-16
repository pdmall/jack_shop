package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/13 15:31
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.util.sql.MySql;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author lvchong
 * @ClassName AnalysisDao
 * @Description 类描述
 * @date 2018/7/13
 */
@Repository
public class AnalysisDao extends DaoBase {
    //交易分析
    public Map<String,Object> trade(Long shop_id , Integer day){
        MySql mySql = new MySql();
        mySql.append("SELECT");
        mySql.append("	COUNT(id) frequency,");
        mySql.append("	SUM(final_price) expenditure,");
        mySql.append("	SUM(final_price) / COUNT(id) avg");
        mySql.append(" FROM");
        mySql.append("	user_order");
        mySql.append("WHERE");
        mySql.append("	shop_id = ?",shop_id);
        mySql.append("AND state > 1");
        mySql.append("AND DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(pay_time)",day);
        return jdbcTemplate.queryForMap(mySql.toString(),mySql.getValues());
    }
    //团购分析
    public Map<String,Object> groupBuy(Long shop_id ,Integer day){
        MySql mySql = new MySql();
        mySql.append("SELECT");
        mySql.append("	COUNT(*) frequency");
        mySql.append(" FROM");
        mySql.append("	user_order uo,");
        mySql.append("	user_order_details uod");
        mySql.append("WHERE");
        mySql.append("	uo.id = uod.user_order_id ");
        mySql.append("AND uod.type = 1");
        mySql.append("AND shop_id = ?",shop_id);
        mySql.append("AND uod.state = 1");
        mySql.append("AND DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(pay_time)" , day);

        MySql mySql2 = new MySql();
        mySql2.append("SELECT");
        mySql2.append("count(DISTINCT(uo.id)) person,");
        mySql2.append("SUM(uod.price) sum_price");
        mySql2.append("FROM");
        mySql2.append("user_order uo,");
        mySql2.append("user_order_details uod");
        mySql2.append("WHERE");
        mySql2.append("uo.id = uod.user_order_id");
        mySql2.append("AND shop_id = ?",shop_id);
        mySql2.append("AND uod.type = 1");
        mySql2.append("AND DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(pay_time)", day);
        Map<String,Object> map =jdbcTemplate.queryForMap(mySql2.toString(),mySql2.getValues());
        map.putAll(jdbcTemplate.queryForMap(mySql.toString(),mySql.getValues()));
        return map;
    }
    //评价分析
    public Map<String,Object> evaluation(Long shop_id ,Integer day){
        MySql mySql = new MySql();
        //所有评论数
        mySql.append("SELECT");
        mySql.append("	COUNT(id) frequency");
        mySql.append("FROM");
        mySql.append("	user_order");
        mySql.append("WHERE");
        mySql.append("	shop_id = ? AND state = 3",shop_id);
        mySql.append("AND DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(pay_time);",day);
        Map<String,Object> map = jdbcTemplate.queryForMap(mySql.toString(),mySql.getValues());
        MySql mySql1 = new MySql();
        //好评数
        mySql1.append("SELECT");
        mySql1.append("	COUNT(*)");
        mySql1.append("FROM");
        mySql1.append("	user_order uo");
        mySql1.append("WHERE");
        mySql1.append("	( uo.service_score + uo.enviro_score + uo.taste_score ) / 3 >= 6  ");
        mySql1.append("	AND shop_id = ? AND state = 3 ",shop_id);
        mySql1.append("AND DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(pay_time);",day);
        map.putAll(jdbcTemplate.queryForMap(mySql.toString(),mySql.getValues()));

        MySql mySql2 = new MySql();
        //新增评论
        mySql2.append("SELECT");
        mySql2.append("	COUNT(id) frequency");
        mySql2.append("FROM");
        mySql2.append("	user_order");
        mySql2.append("WHERE");
        mySql2.append("	shop_id = ?",shop_id);
        mySql2.append("AND state = 3");
        mySql2.append("AND DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(pay_time);",day);
        map.putAll(jdbcTemplate.queryForMap(mySql2.toString(),mySql.getValues()));
        return map;
    }
    //顾客分析
    public Map<String,Object> customer(Long shop_id ,Integer day){
        //新顾客
        String sql = "SELECT count(user_id) new_customer FROM user_order where shop_id = ? AND user_id NOT in(SELECT user_id FROM user_order WHERE shop_id = ? AND NOT(DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(Pay_time))) AND (DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(Pay_time))";
        Map<String,Object> map = jdbcTemplate.queryForMap(sql,shop_id,shop_id,day);
        //老顾客
        String sql2 = "SELECT count(user_id)old_customer FROM user_order where shop_id = ? AND user_id in(SELECT user_id FROM user_order WHERE shop_id = ? AND NOT(DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= date(Pay_time))) AND (DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(Pay_time))";
        map.putAll(jdbcTemplate.queryForMap(sql2,shop_id,shop_id,day));
        return map;
    }
    //营业统计
    public Map<String,Object> business(Long shop_id ,Integer day){
        Map<String,Object> map = trade(shop_id,day);
        MySql mySql2 = new MySql();
        //新增评论
        mySql2.append("SELECT");
        mySql2.append("	COUNT(id) new_comment");
        mySql2.append("FROM");
        mySql2.append("	user_order");
        mySql2.append("WHERE");
        mySql2.append("	shop_id = ?",shop_id);
        mySql2.append("AND state = 3");
        mySql2.append("AND DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(pay_time)",day);
        map.putAll(jdbcTemplate.queryForMap(mySql2.toString(),mySql2.getValues()));
        return map;
    }

}
