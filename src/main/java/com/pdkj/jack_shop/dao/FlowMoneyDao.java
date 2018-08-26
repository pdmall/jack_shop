package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/21 16:36
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.FlowMoney;
import com.pdkj.jack_shop.model.ShopFlowMoney;
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
 * @ClassName FlowMoneyDao
 * @Description 类描述
 * @date 2018/7/21
 */
@Repository
public class FlowMoneyDao extends DaoBase{

    public List<Map<String,Object>> getFlowMoney(Long id,Pager pager) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("    fm.id,fm.`value`,fm.created,s.shop_name,fs.`name`,fs.flow_record_type");
        sql.append("FROM");
        sql.append("	flow_money AS fm,user_order AS uo,shop AS s,flow_state AS fs");
        sql.append("WHERE");
        sql.append(" fm.user_id = ? AND",id);
        sql.append("	fm.flow_state_id = fs.id AND fm.item_id = uo.id AND uo.shop_id = s.id ");
        sql.append("order by fm.created desc");
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }

    public void addFlowMoney(FlowMoney flowMoney){
        flowMoney.setId(Tools.generatorId());
        SqlInfo sqlInfo = SQLTools.getInsertSQL(flowMoney,"flow_money");
        jdbcTemplate.update(sqlInfo.getSql(),sqlInfo.getValues());
    }

    public void addShopFlowMoney(ShopFlowMoney flowMoney){
        flowMoney.setId(Tools.generatorId());
        SqlInfo sqlInfo = SQLTools.getInsertSQL(flowMoney,"shop_flow_money");
        jdbcTemplate.update(sqlInfo.getSql(),sqlInfo.getValues());
    }

    public Object getFlowMoneyGet(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append(" IFNULL(sum(`value`),0) sum ");
        sql.append("FROM");
        sql.append("	flow_money AS fm,flow_state AS fs");
        sql.append("WHERE");
        sql.append("	fm.flow_state_id = fs.id AND");
        sql.append("	fm.user_id = ? AND fs.flow_record_type = 0",id);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }

    public Object getFlowMoneyPay(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append(" IFNULL(sum(`value`),0) sum ");
        sql.append("FROM");
        sql.append("	flow_money AS fm,flow_state AS fs");
        sql.append("WHERE");
        sql.append("	fm.flow_state_id = fs.id AND");
        sql.append("	fm.user_id = ? AND fs.flow_record_type = 1",id);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
}
