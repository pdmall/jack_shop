package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/6/28 9:10
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.Shop;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName ShopDao
 * @Description 类描述
 * @date 2018/6/28
 */
public class ShopDao extends DaoBase<Shop> {
    public ShopDao(){
        rowMap = new BeanPropertyRowMapper<Shop>(Shop.class);
        columnQ = "id,shop_name,shop_address,longitude," +
                "latitude,average_cons,service_score," +
                "enviro_score,taste_score,home_img, detail_imgs";
        columnU =" id, shop_name, shop_address, city, province, shop_phone,  " +
                "  buss_open, buss_close, " +
                " longitude, latitude,  introduce, license_img,home_img, detail_imgs";
        sqlDel = "update shop set shop_state = -1 where id in  ";
    }
    public List<Shop> selectAll(Object[] objects){

        return super.findByCondtion(objects);
    }
    public  Integer save(String sql, Object[] pramts)  {
        return jdbcTemplate.update(sql,pramts);
    };

}
