package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/21 9:45
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.app_constant;
import com.pdkj.jack_shop.util.sql.MySql;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName ParameterDao
 * @Description 类描述
 * @date 2018/7/21
 */

@Repository
public class AppConstantDao extends DaoBase{
    public List<Map<String,Object>> getAppConstant(){
        MySql sql = new MySql();
        sql.append("select * from app_constant");
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
    public Object getPriceConstant(app_constant constant){
        MySql sql = new MySql();
        sql.append("select value from app_constant where id = ?",constant.app_constant());
        return jdbcTemplate.queryForMap(sql.toString(),sql.getValues()).get("value");
    }

}
