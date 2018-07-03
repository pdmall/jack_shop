package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.SignIn;
import org.springframework.boot.Banner;
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
public class SignInDao extends DaoBase<SignIn> {

    public List<Map<String, Object>> getSignInByUserId() {
        String sql = "SELECT id,img_url,`type`,`value` from banner where is_available = 1";
        return jdbcTemplate.queryForList(sql);
    }
}
