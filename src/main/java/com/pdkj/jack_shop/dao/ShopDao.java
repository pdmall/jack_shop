package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.github.pagehelper.Page;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.model.Shop;
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
 * @ClassName ShopDao
 * @Description 类描述
 * @date 2018/6/28
 */
@Repository
public class ShopDao extends DaoBase<Shop> {

    public List<Map<String, Object>> getShopList(Pager page) {
        MySql sql = new MySql();
        sql.append("select id,shop_name,shop_address,longitude,");
        sql.append("latitude,average_cons,service_score,");
        sql.append("enviro_score,taste_score,home_img from shop where shop_state=1");
        sql.limit(page);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }

    public Long addShop(Shop shop) {
        shop.setId(Tools.generatorId());
        SqlInfo insertSQL = SQLTools.getInsertSQL(shop);
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
        return shop.getId();
    }

    public Map<String, Object> getShop(Long id) {
        String sql = " SELECT id,shop_name,shop_address,province,city,county,shop_phone,shop_state,buss_open,buss_close,longitude,latitude,average_cons,eintroduce,license_img,service_score,enviro_score,taste_score,home_img,detail_imgs FROM shop where shop_id = ? ";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);
        return map;
    }

    public Map<String, Object> findAddressById(Long id) {
        String sql = "select id,shop_name,shop_address,province,city,county,shop_phone," +
                "longitude,latitude from shop where id=? ";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public List<Map<String, Object>> findByClassify(Long type_id) throws CustomException {
        String sql = "select " +
                "    shop.id,shop_name,shop_address,longitude," +
                "    latitude,average_cons,service_score," +
                "    enviro_score,taste_score,home_img" +
                "    from shop inner join shop_type_rel on shop.id = shop_type_rel.shop_id where type_id =? ";
        return jdbcTemplate.queryForList(sql, type_id);
    }

    public List<Map<String, Object>> searchBox(String name,String shop_address) {
        MySql sql = new MySql();
        sql.append("select ");
        sql.append("shop.id,shop_name,shop_address,longitude, ");
        sql.append("latitude,average_cons,service_score,");
        sql.append("enviro_score,taste_score,home_img  ");
        sql.append("from shop inner join shop_type_rel on shop.id = shop_type_rel.shop_id ");
        sql.append("inner join shop_type on shop_type.id = shop_type_rel.type_id ");
        sql.append("where");
        String key = SQLTools.FuzzyKey(name);
        sql.append("(shop_type.name like ? OR  shop_name like ?) and shop_address = ? and state  = 1",key,key,shop_address);

        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }
}
