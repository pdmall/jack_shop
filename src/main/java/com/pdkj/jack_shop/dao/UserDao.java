package com.pdkj.jack_shop.dao;

import com.pdkj.jack_shop.configurer.AliYunOSS;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDao extends DaoBase {
    public User getUserByToken(String token) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" * ");
        mySql.append(" from `user` ");
        mySql.append("where token=?", token);
        RowMapper<User> rowMap = new BeanPropertyRowMapper<User>(User.class);
        List<User> users = jdbcTemplate.query(mySql.toString(), mySql.getValues(), rowMap);
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    public User login(String username, String pass) throws Exception {
        pass = Tools.encryptPass(username, pass);
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" *");
        mySql.append(" from `user` ");
        mySql.append("where username=? and password=?", username, pass);
        RowMapper<User> rowMap = new BeanPropertyRowMapper<User>(User.class);
        List<User> users = jdbcTemplate.query(mySql.toString(), mySql.getValues(), rowMap);
        if (users.size() == 0) {
            return null;
        }
        users.get(0).setPassword(null);
        return users.get(0);
    }

    public User getUserByPhone(String phone) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" *");
        mySql.append(" from `user` ");
        mySql.append("where phone=?", phone);
        RowMapper<User> rowMap = new BeanPropertyRowMapper<User>(User.class);
        List<User> users = jdbcTemplate.query(mySql.toString(), mySql.getValues(), rowMap);
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    public Long save(User user) {
        user.setId(Tools.generatorId());
        SqlInfo sqlInfo = SQLTools.getInsertSQL(user);
        jdbcTemplate.update(sqlInfo.getSql(), sqlInfo.getValues());
        return user.getId();
    }

    public Map<String, Object> getUser(Long id) {
        String sql = "Select id,`name` from user where id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public Integer update(User oldUser) {
        SqlInfo sql = SQLTools.getUpdateById(oldUser, "user", oldUser.getId());
        return jdbcTemplate.update(sql.toString(), sql.getValues());
    }

    public User getUserByOpenId(String openid) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" `name`,username,created,updated,phone,nickname,icon,role_id,ip,email,idcard,state,address_id");
        mySql.append(" from `user` where open_id = ?", openid);
        RowMapper<User> rowMap = new BeanPropertyRowMapper(User.class);
        User user = jdbcTemplate.queryForObject(mySql.toString(), mySql.getValues(), rowMap);
        return user;
    }

    public int updateToken(Long id, String token) {
        String sql = "update user set token = ? where id = ?";
        int effect = jdbcTemplate.update(sql, token, id);
        return effect;
    }

    public int updateRole(Long id, Integer role_id) {
        String sql = "update user set role_id = ? where id = ?";
        int effect = jdbcTemplate.update(sql, role_id, id);
        return effect;
    }

    public void delImg(String img_url) {
        AliYunOSS.deleteFile(img_url);
    }

    public List<Map<String, Object>> getRole() {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append("*");
        mySql.append("from");
        mySql.append("role");
        return jdbcTemplate.queryForList(mySql.toString());
    }

    //验证卷
    public Object verifyCoupon(Long user_coupon_rel_id) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" original_price,t.name,c.buy_price,s.shop_name");
        mySql.append("from");
        mySql.append("user_coupon_rel ucr , coupon c,type_of t ,shop s");
        mySql.append("where c.id = ucr.coupon_id AND c.type_of_id = t.id AND s.id = c.shop_id AND");
        mySql.append("ucr.id = ? AND is_use = 1 AND coupon_state = 1", user_coupon_rel_id);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(mySql.toString(), mySql.getValues());
        if (list.get(0) != null) {
            return list.get(0);
        } else {
            return "没有这个卷了哟";
        }
    }

    //验证用户是否有资格扫描卷
    public Integer verifyUser(Long user_id, Long item_rel_id) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append("count(*) count");
        mySql.append("from");
        mySql.append("user_shop_rel usr,coupon c ,user_coupon_rel ucr ");
        mySql.append(" where usr.shop_id = c.shop_id AND ucr.coupon_id = c.id AND");
        mySql.append(" usr.user_id = ? AND ucr.id = ?", user_id, item_rel_id);
        Map<String, Object> map = jdbcTemplate.queryForMap(mySql.toString(), mySql.getValues());
        return Integer.valueOf(map.get("count").toString());
    }

    //验证团餐
    public Object verifyGroupBuy(Long user_coupon_rel_id) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" original_price,to.name ");
        mySql.append("from");
        mySql.append("group_buy gb , user_group_buy_rel ugbr,type_of to ");
        mySql.append("where gb.id = ugbr.group_buy_id AND gb.type_of_id = to.id AND ugbr.id = ? AND is_use = 1 AND coupon_state = 1", user_coupon_rel_id);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(mySql.toString(), mySql.getValues());
        if (list.get(0) != null) {
            return list.get(0);
        } else {
            return "没有这个卷了哟";
        }
    }

    public Map<String,Object> getCouponQR(Long id, Long coupon_id) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" original_price,to.name ");
        mySql.append("from");
        mySql.append(" user_coupon_rel ");
        mySql.append("where user_id = ? AND coupon_id = ? AND is_use = 1 ",id,coupon_id);
        return  jdbcTemplate.queryForList(mySql.toString(), mySql.getValues()).get(0);
    }
}
