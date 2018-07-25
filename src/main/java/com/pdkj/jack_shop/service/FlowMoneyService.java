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
import com.pdkj.jack_shop.model.FlowMoney;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName FlowMoneyService
 * @Description 类描述
 * @date 2018/7/21
 */
@Service
public class FlowMoneyService extends BaseService {
    public Map<String,Object> getFlowMoney(Long id,Pager pager) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("flowsMoneys",flowMoneyDao.getFlowMoney(id,pager));
        map.put("FlowMoneySum",flowMoneyDao.getFlowMoneyReckon(id,pager));
        return map;
    }
    public void addFlowMoney(FlowMoney flowMoney){
        flowMoneyDao.addFlowMoney(flowMoney);
    }
}
