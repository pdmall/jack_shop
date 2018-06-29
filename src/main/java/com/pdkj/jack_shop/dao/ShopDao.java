package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.util.Tools;
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

    public List<Map<String, Object>> getAllShop() {
        String sql = "select id,shop_name,shop_address,longitude," +
                "latitude,average_cons,service_score," +
                "enviro_score,taste_score,home_img from shop where shop_state=1 ";
        return jdbcTemplate.queryForList(sql);
    }

    public Long addShop(Shop shop) {
        shop.setId(Tools.generatorId());
        SqlInfo insertSQL = SQLTools.getInsertSQL(shop);
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
        return shop.getId();
    }

    public Map<String, Object> getShop(Long id) {
        String sql = "select id,shop_name,shop_address,longitude," +
                "latitude,average_cons,service_score," +
                "enviro_score,taste_score,home_img from shop where id = ?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);
        return map;
    }

    public Map<String, Object> findAddressById(Long id) {
        String sql = "select id,shop_name,shop_address,shop_phone," +
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
        String sql = "select " +
                "    shop.id,shop_name,shop_address,longitude, " +
                "    latitude,average_cons,service_score, " +
                "    enviro_score,taste_score,home_img  " +
                "    from shop inner join shop_type_rel on shop.id = shop_type_rel.shop_id " +
                "    inner join shop_type on shop_type.id = shop_type_rel.type_id " +
                "    where " +
                "        shop_type.name like CONCAT('%',?,'%') OR  shop_name like CONCAT('%',?,'%') and shop_address = ?; ";
        Object[] objects = new Object[]{name, name,shop_address};
        return jdbcTemplate.queryForList(sql, objects);
    }
}
