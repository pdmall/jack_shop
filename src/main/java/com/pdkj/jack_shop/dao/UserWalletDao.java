package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/2 10:23
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.core.CustomException;
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
public class UserWalletDao extends DaoBase<UserWallet> {
    public Map<String, Object> getWallet(Object user_id) {
        String sql = "SELECT id,money from user_wallet where wallet_state = 1 and user_id = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, user_id);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    public void save(Long user_id) {
        UserWallet wallet = new UserWallet();
        wallet.setId(Tools.generatorId());
        wallet.setMoney(0.0);
        wallet.setState(1);
        wallet.setUser_id(user_id);
        SqlInfo sqlInfo = SQLTools.getInsertSQL(wallet, "user_wallet");
        jdbcTemplate.update(sqlInfo.getSql(), sqlInfo.getValues());
    }

    public Integer updateMoney(Object buy_price, Object user_id, Integer flow_record_type) {
        MySql mySql = new MySql();
        if (flow_record_type == 0) {
            mySql.append("UPDATE `user_wallet` SET");
            mySql.append("money = money + ?", buy_price);
            mySql.append("where user_id = ? AND wallet_state = 1", user_id);
        } else {
            Map<String, Object> map = getWallet(user_id);
            if (map == null)
                throw new CustomException("钱包被冻结咯");
            if (Double.valueOf(buy_price.toString()) >= Double.valueOf(map.get("money").toString()))
                throw new CustomException("余额不足");
            mySql.append("UPDATE `user_wallet` SET");
            mySql.append("money = money - ?", buy_price);
            mySql.append("where user_id = ? AND wallet_state = 1", user_id);
        }
        return jdbcTemplate.update(mySql.toString(), mySql.getValues());
    }
}
