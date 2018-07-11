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
    public List<Map<String,Object>> getUserConcernList(Long shop_id) {
        List<Map<String,Object>>list = userConcernDao.getUserConcernList(shop_id);
        list.add(userConcernDao.getUserConcernCount(shop_id));
       return list;
    }
}
