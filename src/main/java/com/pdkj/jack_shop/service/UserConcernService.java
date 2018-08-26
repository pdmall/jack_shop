package com.pdkj.jack_shop.service;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.service
 * @author lvchong
 * @date 2018/7/11 9:46
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.UserConcern;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName UserConcernService
 * @Description 类描述
 * @date 2018/7/11
 */
@Service
public class UserConcernService extends BaseService<UserConcern>{
    //获得用户关注列表
    public Map<String,Object> getUserConcernList(Long shop_id) {
        Map<String,Object> data = new HashMap<>();
        List<Map<String,Object>>list = userConcernDao.getUserConcernList(shop_id);
        data.put("concernList",list);
        data.put("count",userConcernDao.getUserConcernCount(shop_id));
       return data;
    }
    //获得用户关注列表
    public List<Map<String,Object>> getShopConcernList(Long user_id) {
        return userConcernDao.getShopConcernList(user_id);
    }


    public Integer concern(Long user_id, UserConcern userConcern){
        if(Integer.valueOf(userConcernDao.isConcern(user_id,userConcern.getShop_id()).get("count").toString())>0){
            return userConcernDao.noConcern(user_id,userConcern.getShop_id(),1);
        }else{
            userConcern.setBe_user_id(user_id);
            userConcern.setIs_cancel(1);
            return userConcernDao.concern(userConcern);
        }
    }

    public Object noConcern(Long user_id, Long shop_id) {
        return userConcernDao.noConcern(user_id,shop_id,0);
    }
    public Object isConcern(Long user_id, Long shop_id){
        return userConcernDao.isConcern(user_id,shop_id);
    }
}
