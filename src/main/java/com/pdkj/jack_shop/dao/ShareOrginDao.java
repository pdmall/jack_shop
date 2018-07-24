package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName ShopDao
 * @Description 类描述
 * @date 2018/6/28
 */
@Repository
public class ShareOrginDao extends DaoBase {

    public List<Map<String, Object>> getMyLevel1(Long id, Pager pager) {
        MySql mySql = new MySql();
        mySql.append("select nickname,so.created,p.`value` from share_orgin so , `user` u ,parameter p ");
        mySql.append(" where so.parameter_id = p.id AND `u`.id = so.level1 AND level2 = ? ");
        mySql.append(" AND date_format(so.created,'%Y-%m')=date_format(now(),'%Y-%m')");
        mySql.append("order by so.created desc", id);
        mySql.limit(pager);
        return jdbcTemplate.queryForList(mySql.toString(), mySql.getValues());
    }

    public List<Map<String, Object>> getMyLevel2(Long id, Pager pager) {
        MySql mySql = new MySql();
        mySql.append("select nickname,so.created,p.`value` from share_orgin so , `user` u ,parameter p ");
        mySql.append(" where so.parameter_id = p.id AND `u`.id = so.level1 AND level3 = ? ");
        mySql.append(" AND date_format(so.created,'%Y-%m')=date_format(now(),'%Y-%m')");
        mySql.append("order by so.created desc", id);
        mySql.limit(pager);
        return jdbcTemplate.queryForList(mySql.toString(), mySql.getValues());
    }

    public Map<String, Object> getMyLevel1Money(Long id) {
        MySql mySql = new MySql();
        mySql.append("select count(*) count,sum(`value`) sum from share_orgin so , `user` u ,parameter p ");
        mySql.append("where so.parameter_id = p.id AND `u`.id = so.level2 AND level3 = ? ", id);
        mySql.append("AND date_format(so.created,'%Y-%m')=date_format(now(),'%Y-%m')");
        return jdbcTemplate.queryForMap(mySql.toString(), mySql.getValues());
    }

    public Map<String, Object> getMyLevel2Money(Long id) {
        MySql mySql = new MySql();
        mySql.append("select count(*) count,sum(`value`) sum from share_orgin so , `user` u ,parameter p ");
        mySql.append("where so.parameter_id = p.id AND `u`.id = so.level1  AND level3 = ? ", id);
        mySql.append("AND date_format(so.created,'%Y-%m')=date_format(now(),'%Y-%m')");
        return jdbcTemplate.queryForMap(mySql.toString(), mySql.getValues());
    }
    public Map<String, Object> getMyAll(Long id) {
        MySql mySql1 = new MySql();
        mySql1.append("select count(*) count,sum(`value`) sum from share_orgin so , `user` u ,parameter p ");
        mySql1.append("where so.parameter_id = p.id AND `u`.id = so.level1  AND level2 = ? ", id);
        Map<String, Object> map = jdbcTemplate.queryForMap(mySql1.toString(), mySql1.getValues());
        MySql mySql2 = new MySql();
        mySql2.append("select count(*) count,sum(`value`) sum from share_orgin so , `user` u ,parameter p ");
        mySql2.append("where so.parameter_id = p.id AND `u`.id = so.level2  AND level3 = ? ", id);
        Map<String, Object> map2 = jdbcTemplate.queryForMap(mySql2.toString(), mySql2.getValues());
        Map<String, Object> map1 =new HashMap<String, Object>();
        map1.put("count",Integer.parseInt(map2.get("count").toString()) + Integer.parseInt(map.get("count").toString()));
        map1.put("sum",(map2.get("sum")==null?0.0:Double.parseDouble(map2.get("sum").toString())) + (map.get("sum")==null?0.0:Double.parseDouble(map.get("sum").toString())));
        return map;
    }


}
