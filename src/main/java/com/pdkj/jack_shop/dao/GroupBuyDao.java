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
import com.pdkj.jack_shop.model.UserGroupBuyRel;
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


    //查询用户购买的套餐
    public List<Map<String, Object>> getGroupBuyByUserId(Long userId, Pager pager) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("gb.title,gb.buy_price,gb.original_price,gb.diners_number,gb.appointment,gb.state group_buy_state,");
        sql.append("gb.unavailable_date,s.shop_name,gb.group_buy_img,gb.type_of_id,uod.order_state_id ,uod.id QR ,uod.use_state,");
        sql.append(" s.id shop_id, uod.id user_order_details_id, uo.id user_order_id");
        sql.append("FROM ");
        sql.append(" group_buy gb ,shop s,user_order_details uod ,user_order uo ");
        sql.append(" WHERE ");
        sql.append("gb.shop_id = s.id AND gb.id = uod.item_id AND uo.id = uod.user_order_id AND");
        sql.append(" uo.user_id = ? AND gb.state > 0  AND uod.type_of_id = 2", userId);
        sql.append("order by gb.state ,uod.order_state_id , uo.created desc");
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    //查询商铺发布的套餐
    public List<Map<String, Object>> getGroupBuyByShopId(Long shop_id, Integer coupon_state, Pager pager) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("gb.title,gb.original_price,gb.buy_price,gb.appointment,gb.date_start,gb.date_end,");
        sql.append("gb.time_start,gb.time_end,gb.group_buy_img,gb.created,gb.shop_id,gb.state,gb.buy_person_limit,");
        sql.append("gb.stock_count,gb.once_count,gb.unavailable_date,gb.diners_number,gb.id,s.shop_name,gb.type_of_id ");
        sql.append("FROM ");
        sql.append("  group_buy gb ,shop s");
        sql.append(" WHERE ");
        sql.append("s.id = gb.shop_id and shop_id = ? and gb.state = ?", shop_id, coupon_state);
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getIsPassGroupBuyList(Long shop_id) {
        MySql sql = new MySql();
        sql.append("SELECT ");
        sql.append("gb.title,gb.original_price,gb.buy_price,gb.appointment,gb.date_start,gb.date_end,");
        sql.append("gb.time_start,gb.time_end,gb.group_buy_img,gb.created,gb.shop_id,gb.state,gb.buy_person_limit,");
        sql.append("gb.stock_count,gb.once_count,gb.unavailable_date,gb.diners_number,gb.id,s.shop_name,gb.type_of_id ");
        sql.append("FROM ");
        sql.append("is_pass_group_buy gb ,shop s where s.id = gb.shop_id and shop_id = ? order by gb.created DESC", shop_id);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getLog(Long id) {
        MySql sql = new MySql();
        sql.append("select * from group_buy_pass_log where shop_id= ?", id);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public void addUserGroupBuyRel(UserGroupBuyRel userGroupBuyRel) {
        userGroupBuyRel.setId(Tools.generatorId());
        SqlInfo insertSQL = SQLTools.getInsertSQL(userGroupBuyRel, "user_group_buy_rel");
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());

    }

    public Map<String , Object> verifyGroupBuy(Long item_id){
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append("shop_id,original_price,buy_price,date_start,date_end,time_start,time_end,title,shop_name");
        mySql.append("from");
        mySql.append("group_buy gb,shop s");
        mySql.append("where");
        mySql.append("gb.shop_id = s.id AND gb.id = ? AND state = 1 ",item_id);
        return jdbcTemplate.queryForMap(mySql.toString(),mySql.getValues());
    }

    public Map<String , Object> getGroupBuy(Object item_id) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append("original_price,buy_price,date_start,date_end,time_start,time_end,title,diners_number");
        mySql.append("from");
        mySql.append("group_buy gb");
        mySql.append("where");
        mySql.append("id = ? ",item_id);
        return jdbcTemplate.queryForMap(mySql.toString(),mySql.getValues());
    }
}
