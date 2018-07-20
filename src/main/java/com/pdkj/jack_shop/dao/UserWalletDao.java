package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/2 10:23
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.UserWallet;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.boot.Banner;
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
public class UserWalletDao extends DaoBase<UserWallet> {
    public List<Map<String, Object>> getWallet(Long user_id) {
        String sql = "SELECT id,user_id,money from user_wallet where state = 1 and user_id = ?";
        return jdbcTemplate.queryForList(sql,user_id);
    }

    public void save(Long user_id) {
        UserWallet wallet = new UserWallet();
        wallet.setId(Tools.generatorId());
        wallet.setMoney(0.0);
        wallet.setState(1);
        wallet.setUser_id(user_id);
        SqlInfo sqlInfo = SQLTools.getInsertSQL(wallet,"user_wallet");
        jdbcTemplate.update(sqlInfo.getSql(),sqlInfo.getValues());

    }
}
