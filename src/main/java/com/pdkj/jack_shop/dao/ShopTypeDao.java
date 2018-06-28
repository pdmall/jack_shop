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
import com.pdkj.jack_shop.model.Shop;
import com.pdkj.jack_shop.model.ShopType;
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
public class ShopTypeDao extends DaoBase<ShopType> {

    public List<Map<String, Object>> getAllShopType() {
        String sql = "SELECT id,`name`,parent_id,img_url FROM `shop_type` where parent_id = 0";
        return jdbcTemplate.queryForList(sql);
    }


}
