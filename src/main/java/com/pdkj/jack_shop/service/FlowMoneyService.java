package com.pdkj.jack_shop.service;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.service
 * @author lvchong
 * @date 2018/7/21 16:25
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.dao.DaoBase;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName FlowMoneyService
 * @Description 类描述
 * @date 2018/7/21
 */

public class FlowMoneyService extends BaseService {
    public List<Map<String,Object>> getFlowMoney(Long id) {
        return flowMoneyDao.getFlowMoney(id);
    }
}
