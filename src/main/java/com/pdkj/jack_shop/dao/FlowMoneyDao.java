package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/21 16:36
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName FlowMoneyDao
 * @Description 类描述
 * @date 2018/7/21
 */
@Repository
public class FlowMoneyDao extends DaoBase{

    public List<Map<String,Object>> getFlowMoney(Long id,Pager pager) {
        MySql sql = new MySql();
        sql.append("select * from ");
        sql.limit(pager);
        return null;
    }

}
