package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.service
 * @author lvchong
 * @date 2018/7/10 14:31
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.GroupBuy;
import com.pdkj.jack_shop.model.IsPassGroupBuy;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName GroupBuyDao
 * @Description 类描述
 * @date 2018/7/10
 */
@Repository
public class GroupBuyDao extends DaoBase<GroupBuy> {
    //添加一个套餐
    public Long addGroupBuy(IsPassGroupBuy isPassGroupBuy) {
        isPassGroupBuy.setId(Tools.generatorId());
        SqlInfo sqlInfo = SQLTools.getInsertSQL(isPassGroupBuy, "is_pass_group_buy");
        jdbcTemplate.update(sqlInfo.getSql(), sqlInfo.getValues());
        return isPassGroupBuy.getId();
    }

    //添加套餐中的菜品
    public void addGroupBuyGoods(Long group_buy_id, String groupBuy_ids) {
        String[] GroupBuy_ids = groupBuy_ids.split(",");
        if (GroupBuy_ids.length > 0) {
            MySql sql = new MySql();
            sql.append("INSERT INTO `group_buy_goods_rel` (`id`, `group_buy_id`, `goods_id`) VALUES ");
            for (String GroupBuy_id : GroupBuy_ids) {
                sql.append("(?, ?, ?),", Tools.generatorId(), group_buy_id, Long.parseLong(GroupBuy_id));
            }
            sql.delLast();
            jdbcTemplate.update(sql.toString(), sql.getValues());
        }
    }

    //查询套餐的详情
    public Map<String, Object> getGroupBuyById(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("title,gb.id,buy_price,original_price,diners_number,appointment,");
        sql.append("unavailable_date,once_count  ");
        sql.append("FROM ");
        sql.append(" group_buy gb , user_group_buy_rel ugbr");
        sql.append(" WHERE ");
        sql.append("gb.id = ugbr.group_buy_id and user_id = ? and state = ? and is_use = ?",id);
        return jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
    }

    //查询用户购买的套餐
    public List<Map<String, Object>> getGroupBuyByUserId(Long userId, Integer coupon_state,Integer is_use) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("title,gb.id,buy_price,original_price,diners_number,appointment,");
        sql.append("unavailable_date,once_count  ");
        sql.append("FROM ");
        sql.append(" group_buy gb , user_group_buy_rel ugbr");
        sql.append(" WHERE ");
        sql.append("gb.id = ugbr.group_buy_id and user_id = ? and state = ? and is_use = ?",userId,coupon_state,is_use);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    //查询商铺发布的套餐
    public List<Map<String, Object>> getGroupBuyByShopId(Long shopId, Integer coupon_state) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("title,gb.id,buy_price,original_price,diners_number,appointment,");
        sql.append("unavailable_date,once_count ,count(item_id) sale_volume ");
        sql.append("FROM ");
        sql.append(" group_buy gb,user_order_details uod");
        sql.append(" WHERE ");
        sql.append("gb.id = uod.item_id and shop_id = ? and  uod.type = 0 and state = ?",shopId,coupon_state);
        sql.append("group by item_id ");
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
    public List<Map<String,Object>> getIsPassGroupBuyList(Long shop_id) {
        MySql sql = new MySql();
        sql.append("SELECT * from is_pass_group_buy order where shop_id = ? order by created DESC",shop_id);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
    public List<Map<String,Object>> getLog(Long id){
        MySql sql = new MySql();
        sql.append("select * from group_buy_pass_log where shop_id= ?",id);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
}
