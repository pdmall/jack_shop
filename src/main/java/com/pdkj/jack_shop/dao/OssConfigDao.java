package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/8/16 10:39
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.util.sql.MySql;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author lvchong
 * @ClassName OssConfigDao
 * @Description 类描述
 * @date 2018/8/16
 */
@Repository
public class OssConfigDao extends  DaoBase<LabelDao> {
    public Object getOssConfig(Integer id) {
        MySql mySql = new MySql();
        mySql.append("select * from oss_config where id = ?",id);
        return jdbcTemplate.queryForMap(mySql.toString(),mySql.getValues());
    }
}
