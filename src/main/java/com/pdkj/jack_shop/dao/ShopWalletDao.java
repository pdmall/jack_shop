package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/2 10:23
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.ShopWallet;
import com.pdkj.jack_shop.model.UserWallet;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName UserWalletDao
 * @Description 类描述
 * @date 2018/7/2
 */

@Repository
public class ShopWalletDao extends DaoBase<UserWallet> {
    public List<Map<String, Object>> getWallet(Long shop_id) {
        String sql = "SELECT id,user_id,money from shop_wallet where state = 1 and shop_id = ?";
        return jdbcTemplate.queryForList(sql,shop_id);
    }

    public void save(Long shop_id) {
        ShopWallet wallet = new ShopWallet();
        wallet.setId(Tools.generatorId());
        wallet.setMoney(0.0);
        wallet.setWallet_state(1);
        wallet.setShop_id(shop_id);
        SqlInfo sqlInfo = SQLTools.getInsertSQL(wallet,"shop_wallet");
        jdbcTemplate.update(sqlInfo.getSql(),sqlInfo.getValues());
    }

}
