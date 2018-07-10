package com.pdkj.jack_shop.dao;

import com.pdkj.jack_shop.configurer.AliYunOSS;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.util.Tools;
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
        String sql = "SELECT * from USER where token=?";
        RowMapper<User> rowMap = new BeanPropertyRowMapper<User>(User.class);
        List<User> users = jdbcTemplate.query(sql, new Object[]{token}, rowMap);
        if (users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    public User login(String username, String pass) throws Exception {
        pass = Tools.encryptPass(username, pass);
        String sql = "SELECT * from USER where username=? and password=?";
        RowMapper<User> rowMap = new BeanPropertyRowMapper<User>(User.class);
        List<User> users = jdbcTemplate.query(sql, new Object[]{username, pass}, rowMap);
        if (users.size() == 0) {
            return null;
        }
        users.get(0).setPassword(null);
        return users.get(0);
    }

    public User getUserByPhone(String phone) {
        String sql = "SELECT * from USER where phone=?";
        RowMapper<User> rowMap = new BeanPropertyRowMapper<User>(User.class);
        List<User> users = jdbcTemplate.query(sql, new Object[]{phone}, rowMap);
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

    public Map<String, Object> getUser(Long id){
        String sql ="Select id,`name` from user where id = ?";
        return jdbcTemplate.queryForMap(sql,id);
    }

    public void update(User oldUser) {
        SqlInfo sql = SQLTools.getUpdateById(oldUser, "user", oldUser.getId());
        jdbcTemplate.update(sql.toString(),sql.getValues());
    }

    public List<Map<String,Object>> getLevel2ByLevel3(Long id) {
        String sql = "select nickname from share_orgin inner join `user` on `user`.id = share_orgin.level2 where level3 = ? order by created desc";
        return  jdbcTemplate.queryForList(sql,id);
    }
    public List<Map<String,Object>> getLevel1ByLevel3(Long id) {
        String sql = "select nickname from share_orgin inner join `user` on `user`.id = share_orgin.level1 where level3 = ? order by created desc";
        return  jdbcTemplate.queryForList(sql,id);
    }

    public User getUserByOpenId(String openid) {
        String sql = "select * from user where open_id = ?";
        RowMapper<User> rowMap = new BeanPropertyRowMapper(User.class);
        User user = jdbcTemplate.queryForObject(sql, new Object[]{openid}, rowMap);
        return  user;
    }

    public int updateToken(Long id, String token) {
        String sql = "update user set token = ? where id = ?";
        int effect = jdbcTemplate.update(sql, token, id);
        return effect;
    }

    public void delImg(String img_url){
        AliYunOSS.deleteFile(img_url);
    }
}
