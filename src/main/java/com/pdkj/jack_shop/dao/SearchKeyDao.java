package com.pdkj.jack_shop.dao;
/**
 * @Project: shop_seller
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/8/22 14:25
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.model.SearchKey;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName SearchKeyDao
 * @Description 类描述
 * @date 2018/8/22
 */
@Repository
public class SearchKeyDao extends DaoBase {
    public List<Map<String, Object>> searchKey(String key) {
        String item = SQLTools.FuzzyKey(key);
        MySql mySql = new MySql();
        mySql.append("select id,item from search_key where item like ?", item);
        return jdbcTemplate.queryForList(mySql.toString(), mySql.getValues());
    }

    public Object hasSearchKey(String key) {
        MySql mySql = new MySql();
        mySql.append("select id from search_key where item = ?", key);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(mySql.toString(), mySql.getValues());
        if (list.size() == 0)
            return null;
        return list.get(0).get("id");
    }

    public Object addSearchKey(String key) {
        Object o = hasSearchKey(key);
        if (o != null)
            return o;
        SearchKey searchKey = new SearchKey();
        searchKey.setId(Tools.generatorId());
        searchKey.setItem(key);
        SqlInfo sqlInfo = SQLTools.getInsertSQL(searchKey, "search_key");
        if (jdbcTemplate.update(sqlInfo.getSql(), sqlInfo.getValues()) > 0)
            return searchKey.getId();
        throw new CustomException("添加失败");
    }


    public void addSearchKeyRel(Long shopId, Object itemId) {
        if(hasSearchKeyRel(shopId,itemId)){
            MySql mySql = new MySql();
            mySql.append("INSERT INTO `shop_search_key_rel` (`id`, `shop_id`, `search_key_id`) ");
            mySql.append("VALUES (?, ?, ?)",Tools.generatorId(),shopId,itemId);
            jdbcTemplate.update(mySql.toString(), mySql.getValues());
        }
    }
    public boolean hasSearchKeyRel(Long shopId, Object itemId) {
        MySql mySql = new MySql();
        mySql.append("  select count(*) count from `shop_search_key_rel` where shop_id = ? AND search_key_id = ?",shopId,itemId);
        Map<String,Object> map = jdbcTemplate.queryForMap(mySql.toString(), mySql.getValues());
        if (map.get("count").toString().equals("0")){
            return true;
        }
        return false;
    }
    public List<Map<String,Object>> getShopSearchKey(Object shop_id){
        MySql mySql = new MySql();
        mySql.append("select * from shop_search_key_rel sskr,search_key sk where sskr.shop_id = ? AND sskr.search_key_id = sk.id",shop_id);
        return jdbcTemplate.queryForList(mySql.toString(), mySql.getValues());
    }
}
