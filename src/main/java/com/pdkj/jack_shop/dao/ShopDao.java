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
import com.pdkj.jack_shop.model.IsPassShop;
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
        sql.append("select DISTINCT(shop.id),shop_name,shop_address,longitude,");
        sql.append("latitude,average_cons,service_score,street,");
        sql.append("enviro_score,taste_score,home_img,label.name");
        sql.append("from shop inner join shop_type_rel on shop.id = shop_type_rel.shop_id ");
        sql.append("inner join shop_type on shop_type.id = shop_type_rel.type_id ");
        sql.append("inner join label on shop.id = label.shop_id ");
        sql.append(" where shop_state=1 ");
        sql.limit(page);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }


    public Map<String, Object> getShop(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append(" shop.id,name,shop_name,shop_address,province,street,city,county,shop_phone,shop_state,buss_open,buss_close,");
        sql.append(" longitude,latitude,average_cons,introduce,license_img,service_score,enviro_score,taste_score,");
        sql.append(" home_img,detail_imgs FROM shop,label where shop.id = ? and label.shop_id = shop.id",id);
        Map<String, Object> map = jdbcTemplate.queryForMap(sql.toString(), sql.getValues());
        return map;
    }

    public Map<String, Object> findAddressById(Long id) {
        String sql = "select id,shop_name,shop_address,province,city,county,shop_phone," +
                "longitude,latitude from shop where id=? ";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public List<Map<String, Object>> findByClassify(Long type_id, Pager pager) throws CustomException {
        MySql sql = new MySql();
        sql.append("select ");
        sql.append("    shop.id,shop_name,shop_address,longitude,");
        sql.append("    latitude,average_cons,service_score,");
        sql.append("    enviro_score,taste_score,home_img");
        sql.append("    from shop inner join shop_type_rel on shop.id = shop_type_rel.shop_id where type_id =?", type_id);
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> searchBox(String name, String county, Pager pager) {
        MySql sql = new MySql();
        sql.append("select ");
        sql.append("shop.id,shop_name,shop_address,longitude, ");
        sql.append("latitude,average_cons,service_score,");
        sql.append("enviro_score,taste_score,home_img  ");
        sql.append("from shop inner join shop_type_rel on shop.id = shop_type_rel.shop_id ");
        sql.append("inner join shop_type on shop_type.id = shop_type_rel.type_id ");
        sql.append("where");
        String key = SQLTools.FuzzyKey(name);
        sql.append("(shop_type.name like ? OR  shop_name like ?) and county = ? and shop_state  = 1", key, key, county);
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }


    public List<Map<String, Object>> shopSort(String name, Long type_id, String county, Pager pager) {
        MySql sql = new MySql();
        sql.append("select ");
        sql.append("shop.id,shop_name,shop_address,longitude, ");
        sql.append("latitude,(average_cons+service_score+");
        sql.append("enviro_score)/3 score,taste_score,home_img  ");
        sql.append("from shop inner join shop_type_rel on shop.id = shop_type_rel.shop_id ");
        sql.append("inner join shop_type on shop_type.id = shop_type_rel.type_id ");
        sql.append("where");
        String key = SQLTools.FuzzyKey(name);
        sql.append(" (shop_type.name like ? OR  shop_name like ?) and type_id =? and county = ? and shop_state  = 1 order by score DESC", key, key, type_id, county);
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    /*SQRT((39.513341-`longitude`)*(39.513341-`longitude`)+(39.513341-`latitude`)*(39.513341-`latitude`))*/
    public List<Map<String, Object>> shopDistanceSort(String name, Long type_id, String county, Pager pager, String latitude, String longitude) {
        MySql sql = new MySql();
        sql.append("select ");
        sql.append("shop.id,shop_name,shop_address,longitude, ");
        sql.append("latitude,average_cons,service_score,");
        sql.append("enviro_score,taste_score,home_img  ");
        sql.append("from shop inner join shop_type_rel on shop.id = shop_type_rel.shop_id ");
        sql.append("inner join shop_type on shop_type.id = shop_type_rel.type_id ");
        sql.append("where");
        String key = SQLTools.FuzzyKey(name);
        sql.append(" (shop_type.name like ? OR  shop_name like ?) and type_id =? and county = ? and shop_state  = 1 order by SQRT((?-`longitude`)*(?-`longitude`)+(?-`latitude`)*(?-`latitude`)) ", key, key, type_id, county, longitude, longitude, latitude, latitude);
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> shopDistanceValueSort(String name, Long type_id, String county, Pager pager, String latitude, String longitude, int distance) {
        MySql sql = new MySql();
        sql.append("select ");
        sql.append("DISTINCT(shop.id),shop_name,shop_address,longitude, ");
        sql.append("latitude,average_cons,service_score,");
        sql.append("enviro_score,taste_score,home_img  ");
        sql.append("from shop inner join shop_type_rel on shop.id = shop_type_rel.shop_id ");
        sql.append("inner join shop_type on shop_type.id = shop_type_rel.type_id ");
        sql.append("where");
        String key = SQLTools.FuzzyKey(name);
        sql.append("SQRT((?-`longitude`)*(?-`longitude`)+(?-`latitude`)*(?-`latitude`)) <= ? and ");
        sql.append(" (shop_type.name like ? OR  shop_name like ?) and type_id =? and county = ? and shop_state  = 1 order by SQRT((?-`longitude`)*(?-`longitude`)+(?-`latitude`)*(?-`latitude`)) ", longitude, longitude, latitude, latitude, distance, key, key, type_id, county, longitude, longitude, latitude, latitude);
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> shopMealTime(String county, Pager pager, Long mealTimeId) {
        MySql sql = new MySql();
        sql.append("select ");
        sql.append("DISTINCT(shop.id),shop_name,shop_address,longitude, ");
        sql.append("latitude,average_cons,service_score,");
        sql.append("enviro_score,taste_score,home_img  ");
        sql.append("from shop inner join shop_meal_time_rel on shop.id = shop_meal_time_rel.shop_id ");
        sql.append("inner join shop_type_rel on shop.id = shop_type_rel.shop_id ");
        sql.append("where");
        sql.append("shop_meal_time_rel.meal_time_id = ? and type_id = 2 and county = ? and shop_state  = 1", mealTimeId, county);
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getShopName(String name, Pager pager) {
        MySql sql = new MySql();
        sql.append("select ");
        sql.append(" DISTINCT(shop_name) ");
        sql.append(" from shop   ");
        sql.append(" where");
        String key = SQLTools.FuzzyKey(name);
        sql.append(" shop_name like ? ", key);
        sql.limit(pager);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public List<Map<String, Object>> getShopPassFinish(Long id){
        MySql sql = new MySql();
        sql.append("SELECT  ");
        sql.append("spl.id,spl.shop_id,reason,created");
        sql.append("FROM");
        sql.append("shop_pass_log AS spl ,user_shop_rel AS usr");
        sql.append("WHERE");
        sql.append("spl.shop_id =  usr.shop_id AND");
        sql.append("usr.user_id = ?",id);
        return jdbcTemplate.queryForList(sql.toString(), sql.getValues());
    }

    public Long addShop(IsPassShop shop) {
        shop.setId(Tools.generatorId());
        SqlInfo insertSQL = SQLTools.getInsertSQL(shop,"is_pass_shop");
        jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
        return shop.getId();
    }
    //添加商铺菜品
    public void addShopGoodsRel(Long shop_id,Long goods_id){
        String sql = "INSERT INTO shop_goods_rel (`id`, `shop_id`, `goods_id`) VALUES (?, ?, ?);";
        jdbcTemplate.update(sql,Tools.generatorId(),shop_id,goods_id);
    }

}
