package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.model.UserOrderDetails;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.Pager;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName ShopDao
 * @Description 类描述
 * @date 2018/6/28
 */
@Repository
public class UserOrderDao extends DaoBase<Banner> {
    //添加订单
    public Long addOrder(UserOrder userOrder) {
        userOrder.setId(Tools.generatorId());
        SqlInfo sqlInfo = SQLTools.getInsertSQL(userOrder, "user_order");
        jdbcTemplate.update(sqlInfo.getSql(), sqlInfo.getValues());
        return userOrder.getId();
    }

    //添加订单详情
    public void addOrderDetails(UserOrderDetails userOrderDetails) {
        userOrderDetails.setId(Tools.generatorId());
        SqlInfo sqlInfo = SQLTools.getInsertSQL(userOrderDetails, "user_order_details");
        jdbcTemplate.update(sqlInfo.getSql(), sqlInfo.getValues());
    }

    //修改订单
    public Long updateOrder(UserOrder userOrder) {
        SqlInfo insertSQL = SQLTools.getUpdateById(userOrder, "user_order", userOrder.getId());
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
        return userOrder.getId();
    }

    //查看订单列表
    public List<Map<String, Object>> getShopOrder(Long shop_id, Pager page) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("	uo.id,");
        sql.append("	COUNT(uo.id),");
        sql.append("	uo.total_price ,");
        sql.append("	os.`name`  ");
        sql.append("FROM");
        sql.append("user_order AS uo ,");
        sql.append("user_order_details AS uod ,");
        sql.append("order_state AS os ");
        sql.append("WHERE ");
        sql.append("	uo.order_state_id = os.id AND");
        sql.append("	uo.id = uod.user_order_id AND");
        sql.append("	uo.user_id = 6 ");
        sql.append("GROUP BY uo.id");
        sql.limit(page);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    //获得用户订单
    public List<Map<String, Object>> getUserOrder(Long user_id, Integer order_state_id,Pager page) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("s.shop_name,uo.id,uo.quantity,uo.created,order_state_id");
        sql.append("FROM");
        sql.append("user_order uo,shop s");
        sql.append("WHERE");
        sql.append("s.id = uo.shop_id AND uo.order_state_id = os.id AND");
        sql.append("uo.user_id  = ?  ",user_id);
        if(order_state_id != 0){
            sql.append("AND uo.order_state_id  = ? ",order_state_id);
        }
        sql.append("order by uo.created desc");
        sql.limit(page);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    //获得订单最终需要支付价格
    public String getOrderPrice(String order_id) {
        String sql = "select final_price from user_order where id =  ? and order_state_id = 1";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, order_id);
        if (maps.size() > 0) {
            return maps.get(0).get("final_price").toString();
        }
        return null;
    }

    //支付是修改订单状态
    public void paySuccess(String orderId, Date pay_time, Integer trade_type) {
        MySql sql = new MySql();
        sql.append("update user_order set order_state_id = 2 ,pay_time = ? ,pay_type = ?  where id = ? and order_state_id = 1",  pay_time,trade_type, orderId);
        jdbcTemplate.update(sql.toString(), sql.getValues());
    }
    //获得订单信息
    public List<Map<String, Object>> getOrder(String orderId){
        MySql sql = new MySql();
        sql.append("select uo.id,user_id,type_of_id,final_price,item_id from user_order uo,user_order_details uod where uo.id = uod.user_order_id and uo.id = ?",orderId);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    //获得订单信息
    public Map<String, Object> getOrderInfo(String pay_on){
        MySql sql = new MySql();
        sql.append("select id,final_price from user_order uo where  uo.pay_on = ?",pay_on);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }
    //修改订单状态根据id
    public void updateOrderRefund(String out_refund_no,Integer order_state_id) {
        MySql sql = new MySql();
        sql.append("update user_order_details set order_state_id = ? where id = ?",order_state_id,out_refund_no);
        jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }
    //获得订单信息
    public Map<String, Object> getOrderByPayOn(String out_trade_no){
        MySql sql = new MySql();
        sql.append("select uo.id,user_id,type_of_id from user_order uo,user_order_details uod where uo.id = uod.user_order_id and uod.id = ?",out_trade_no);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getDetails(String orders, Object id) {
        MySql sql = new MySql();
        sql.append("select");
        sql.append(" id,price");
        sql.append("FROM");
        sql.append("user_order_details ");
        sql.append("where");
        sql.append("user_order_id =? AND state = 0 AND id in(?)",id,orders);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    //获得订单详情id
    public Map<String,Object> getUserOrderDetails(Long user_order_details) {
        MySql sql = new MySql();
        sql.append("select");
        sql.append(" user_order_id,price,item_id,type_of_id,order_state_id");
        sql.append("FROM");
        sql.append("user_order_details ");
        sql.append("where");
        sql.append("id = ?",user_order_details);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }
    //获得订单商铺id
    public Map<String,Object> getShopIdByOrderId(Long user_order_id) {
        MySql sql = new MySql();
        sql.append("select");
        sql.append(" shop_id,user_id");
        sql.append("FROM");
        sql.append(" user_order ");
        sql.append("where");
        sql.append("id = ?",user_order_id);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getDetailsQR(Long order_id) {
        MySql sql = new MySql();
        sql.append("select");
        sql.append(" id");
        sql.append("FROM");
        sql.append("user_order_details ");
        sql.append("where");
        sql.append("user_order_id = ?",order_id);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
}
