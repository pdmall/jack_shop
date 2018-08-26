package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.util.Tools;
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
        mySql.append("select u.phone,so.created,price_constant  from share_orgin so , `user` u ");
        mySql.append(" where  `u`.id = so.level1 AND level2 = ? ", id);
        mySql.append("order by so.created desc");
        mySql.limit(pager);
        return jdbcTemplate.queryForList(mySql.toString(), mySql.getValues());
    }

    public List<Map<String, Object>> getMyLevel2(Long id, Pager pager) {
        MySql mySql = new MySql();
        mySql.append("select u.phone,so.created,price_constant from share_orgin so , `user` u ");
        mySql.append(" where  `u`.id = so.level1 AND level3 = ? ", id);
        mySql.append("order by so.created desc");
        mySql.limit(pager);
        return jdbcTemplate.queryForList(mySql.toString(), mySql.getValues());
    }

    public Map<String, Object> getMyLevel1Money(Long id) {
        MySql mySql = new MySql();
        mySql.append("select count(*) count,sum(price_constant) sum from share_orgin so  ");
        mySql.append("where level2 = ? ", id);
/*        mySql.append("AND date_format(so.created,'%Y-%m')=date_format(now(),'%Y-%m')");*/
        return jdbcTemplate.queryForMap(mySql.toString(), mySql.getValues());
    }

    public Map<String, Object> getMyLevel2Money(Long id) {
        MySql mySql = new MySql();
        mySql.append("select count(*) count,sum(price_constant) sum from share_orgin so  ");
        mySql.append("where level3 = ? ", id);
/*        mySql.append("AND date_format(so.created,'%Y-%m')=date_format(now(),'%Y-%m')");*/
        return jdbcTemplate.queryForMap(mySql.toString(), mySql.getValues());
    }
    public Map<String, Object> getMyAll(Long id) {
        MySql mySql1 = new MySql();
        mySql1.append("select count(*) count,sum(price_constant) sum from share_orgin so ");
        mySql1.append("where  level2 = ? ", id);
        Map<String, Object> map = jdbcTemplate.queryForMap(mySql1.toString(), mySql1.getValues());
        MySql mySql2 = new MySql();
        mySql2.append("select count(*) count,sum(price_constant) sum from share_orgin so ");
        mySql2.append("where  level3 = ? ", id);
        Map<String, Object> map2 = jdbcTemplate.queryForMap(mySql2.toString(), mySql2.getValues());
        Map<String, Object> map1 =new HashMap<String, Object>();
        map1.put("count",Integer.parseInt(map2.get("count").toString()) + Integer.parseInt(map.get("count").toString()));
        map1.put("sum",(map2.get("sum")==null?0.0:Double.parseDouble(map2.get("sum").toString())) + (map.get("sum")==null?0.0:Double.parseDouble(map.get("sum").toString())));
        return map1;
    }


    public void updateDividends(Double dividends, Long user_id) {
        MySql mySql = new MySql();
        mySql.append("UPDATE `share_orgin` SET");
        mySql.append(" dividends = dividends + ? where level1 = ?",dividends,user_id);
        jdbcTemplate.update(mySql.toString(),mySql.getValues());
    }

    public Long getMyLevel3Id(Long user_id) {
        MySql mySql = new MySql();
        mySql.append(" select ");
        mySql.append(" Level3 ");
        mySql.append("from share_orgin where level1 = ?",user_id);
        List<Map<String,Object>> list =  jdbcTemplate.queryForList(mySql.toString(),mySql.getValues());
        if (list.size() == 0)
            return null;
        return Long.parseLong(list.get(0).get("level2").toString());
    }

    public List<Map<String,Object>> getShareOrgin(Long user_id,Integer flow_state_id) {
        MySql mySql = new MySql();
        mySql.append(" select ");
        mySql.append(" value,created,item_id ");
        mySql.append("from flow_money where user_id = ? AND flow_state_id = ?",user_id,flow_state_id);
        return jdbcTemplate.queryForList(mySql.toString(),mySql.getValues());
    }
    public Map<String,Object> getShareOrginSum(Long user_id,Integer flow_state_id) {
        MySql mySql = new MySql();
        mySql.append(" select ");
        mySql.append(" count(*) count,sum(value) sum");
        mySql.append("from flow_money where user_id = ? AND flow_state_id = ?",user_id,flow_state_id);
        return jdbcTemplate.queryForMap(mySql.toString(),mySql.getValues());
    }
    public Long getMyLevel2Id(Long user_id) {
        MySql mySql = new MySql();
        mySql.append(" select ");
        mySql.append(" level2 ");
        mySql.append("from share_orgin where level1 = ? ",user_id);
        List<Map<String,Object>> list =  jdbcTemplate.queryForList(mySql.toString(),mySql.getValues());
        if (list.size() == 0)
            return null;
        return Long.parseLong(list.get(0).get("level2").toString());
    }
    public List<Map<String, Object>> getMyAllParent(Object user_id) {
        MySql mySql = new MySql();
        mySql.append(" select ");
        mySql.append(" Level3,Level2 ");
        mySql.append("from share_orgin where level1 = ?",user_id);
        return jdbcTemplate.queryForList(mySql.toString(),mySql.getValues());
    }
    public Integer addShareOrgin(Long id, Long id1, Long id2, Object priceConstant) {
        MySql mySql = new MySql();
        mySql.append(" INSERT INTO `share_orgin` ");
        mySql.append(" (id,`level1`, `level2`, `level3`, `price_constant`) ");
        mySql.append("VALUES");
        mySql.append("(?,?,?,?,?)",Tools.generatorId(),id,id1,id2,priceConstant);
        return jdbcTemplate.update(mySql.toString(),mySql.getValues());
    }
}
