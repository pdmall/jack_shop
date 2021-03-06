package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.UserConcern;
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
public class UserConcernDao extends DaoBase<UserConcern> {
    //获得用户关注商铺列表
    public List<Map<String, Object>> getUserConcernList(Long shop_id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("uc.created,u.id,u.nickname,u.icon");
        sql.append("FROM");
        sql.append("user_concern AS uc , `user` AS u");
        sql.append("WHERE");
        sql.append("uc.be_user_id = u.id AND uc.shop_id = ? AND uc.is_cancel = 1 ",shop_id);
        sql.append("order by uc.created desc");
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
    //获得全部商铺全部粉丝列表
    public List<Map<String, Object>> getShopConcernList(Long user_id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("uc.created,s.id,s.shop_name,s.home_img,s.street,l.name label_name");
        sql.append("FROM");
        sql.append("user_concern uc ,shop s , label l");
        sql.append("WHERE");
        sql.append("uc.shop_id = s.id AND s.id = l.shop_id AND uc.be_user_id = ? AND uc.is_cancel = 1 ",user_id);
        sql.append("order by uc.created desc");
        return jdbcTemplate.queryForList(sql.toString(),sql.getValues());
    }
    //获得关注量
    public Map<String, Object> getUserConcernCount(Long shop_id) {
        MySql sql = new MySql();
        sql.append("SELECT");
        sql.append("count(*) count");
        sql.append("FROM");
        sql.append("user_concern AS uc , `user` AS u");
        sql.append("WHERE");
        sql.append("uc.be_user_id = u.id AND uc.shop_id = ?",shop_id);
        return jdbcTemplate.queryForMap(sql.toString(),sql.getValues());
    }
    //关注
    public Integer concern(UserConcern userConcern){
        userConcern.setId(Tools.generatorId());
         SqlInfo sqlInfo = SQLTools.getInsertSQL(userConcern,"user_concern");
        return jdbcTemplate.update(sqlInfo.getSql(),sqlInfo.getValues());
    }
    //取消关注
    public Integer noConcern(Long user_id, Long shop_id,Integer cancel) {
        MySql sql = new MySql();
        sql.append("update user_concern set is_cancel = ? where be_user_id = ? AND shop_id = ? ",cancel,user_id,shop_id);
        return jdbcTemplate.update(sql.toString(),sql.getValues());
    }
    //是否关注
    public Map<String, Object> isConcern(Long user_id, Long shop_id){
        MySql sql = new MySql();
        sql.append("select count(*) count from user_concern where be_user_id = ? AND shop_id = ? AND is_cancel = 1 ",user_id,shop_id);
        return jdbcTemplate.queryForMap(sql.toString(),sql.getValues());
    }
}
