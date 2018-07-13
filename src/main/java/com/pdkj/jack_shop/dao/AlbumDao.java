package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/13 10:00
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.Album;
import com.pdkj.jack_shop.util.sql.MySql;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName album
 * @Description 类描述
 * @date 2018/7/13
 */
@Repository
public class AlbumDao extends  DaoBase<Album>{
    public List<Map<String,Object>> getUserPhoto(Long user_id){
        MySql mySql = new MySql();
        mySql.append("select id,shop_id,user_id,img_url,created from album where user_id = ?",user_id);
        return jdbcTemplate.queryForList(mySql.toString(),mySql.getValues());
    }
    public List<Map<String,Object>> getShopPhoto(Long shop_id){
        MySql mySql = new MySql();
        mySql.append("select id,shop_id,user_id,img_url,created from album where shop_id = ?" , shop_id);
        return jdbcTemplate.queryForList(mySql.toString(),mySql.getValues());
    }
    public List<Map<String,Object>> getGoodsPhoto(Long user_id){
        MySql mySql = new MySql();
        mySql.append("select id,shop_id,user_id,img_url,created from album a , goods g ");
        mySql.append(" where g.shop_id = a.shop_id and user_id = ?",user_id);
        return jdbcTemplate.queryForList(mySql.toString(),mySql.getValues());
    }

}
