package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/4 18:52
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.Label;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.stereotype.Repository;

/**
 * @author lvchong
 * @ClassName LabelDao
 * @Description 类描述
 * @date 2018/7/4
 */
@Repository
public class LabelDao extends DaoBase<LabelDao> {
    public int addLabel(Label label) {
        label.setId(Tools.generatorId());
        SqlInfo insertSQL = SQLTools.getInsertSQL(label);
        return jdbcTemplate.update(insertSQL.getSql(), insertSQL.getValues());
    }
}
