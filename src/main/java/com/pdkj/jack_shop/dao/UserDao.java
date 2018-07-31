package com.pdkj.jack_shop.dao;

import com.pdkj.jack_shop.configurer.AliYunOSS;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.MySql;
import com.pdkj.jack_shop.util.sql.SQLTools;
import com.pdkj.jack_shop.util.sql.SqlInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    public void updateRole(Long id, Integer role_id) {
        String sql = "update user set role_id = ? where id = ?";
        jdbcTemplate.update(sql, role_id, id);
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

    //验证用户是否有资格扫描卷
    public Integer verifyUser(Long user_id,Long shop_id) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append("count(*) count");
        mySql.append("from");
        mySql.append("user_shop_rel");
        mySql.append(" where ");
        mySql.append(" user_id = ? AND shop_id = ?", user_id,shop_id);
        Map<String, Object> map = jdbcTemplate.queryForMap(mySql.toString(), mySql.getValues());
        return Integer.valueOf(map.get("count").toString());
    }


    public Map<String,Object> getUserByShopId(Object shop_id) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" user_id ");
        mySql.append("from");
        mySql.append(" user_shop_rel ");
        mySql.append("where shop_id = ? AND master = 1 ", shop_id);
        return jdbcTemplate.queryForList(mySql.toString(), mySql.getValues()).get(0);
    }

    public Integer getUserRole(String item_rel_id,Integer role_id) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" count(*) count");
        mySql.append("from");
        mySql.append(" user ");
        mySql.append("where id = ? AND role_id = ?", item_rel_id,role_id);
        return Integer.valueOf(jdbcTemplate.queryForMap(mySql.toString(), mySql.getValues()).get("count").toString());
    }

    public Map<String,Object> verifyCoupon(Long user_order_details) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append(" ");
        mySql.append("from");
        mySql.append(" var ");
        mySql.append("where id = ? AND role_id = ?", user_order_details);
        return jdbcTemplate.queryForMap(mySql.toString(), mySql.getValues());
    }

    public List<Map<String, Object>> getRoleById(Object id) {
        MySql mySql = new MySql();
        mySql.append("select");
        mySql.append("*");
        mySql.append("from");
        mySql.append("role");
        mySql.append("where id = ?",id);
        return jdbcTemplate.queryForList(mySql.toString());
    }
}
