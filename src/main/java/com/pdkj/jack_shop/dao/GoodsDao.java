package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.Goods;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
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
public class GoodsDao extends DaoBase<Goods> {

    public List<Map<String, Object>> getGroupBuyGoods(Long group_buy_id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("sg.id,sg.name,sg.price,sg.img_url,sg.describe,sgu.name unit");
        sql.append("FROM");
        sql.append("shop_goods AS sg ,shop_goods_unit AS sgu ,group_buy_goods_rel gbgr");
        sql.append("WHERE");
        sql.append("sg.unit_id = sgu.id AND sg.id = gbgr.goods_id AND gbgr.group_buy_id = ?",group_buy_id);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
    public List<Map<String, Object>> getShopGoods(Long shop_id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("sg.id,sg.name,sg.price,sg.img_url,sg.describe,sgu.name unit");
        sql.append("FROM");
        sql.append("shop_goods AS sg ,shop_goods_unit AS sgu ,shop_goods_rel sgr");
        sql.append("WHERE");
        sql.append("sg.unit_id = sgu.id AND sg.id = sgr.shop_id AND cgr.shop_id=?",shop_id);
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }

    public Long addGoods(Goods goods){
        goods.setId(Tools.generatorId());
        SqlInfo sqlInfo = SQLTools.getInsertSQL(goods, "goods");
        jdbcTemplate.update(sqlInfo.getSql(), sqlInfo.getValues());
        return goods.getId();
    }

}
