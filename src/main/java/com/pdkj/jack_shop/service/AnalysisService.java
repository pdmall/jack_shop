package com.pdkj.jack_shop.service;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.service
 * @author lvchong
 * @date 2018/7/13 20:28
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.dao.AnalysisDao;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lvchong
 * @ClassName AnalysisService
 * @Description 类描述
 * @date 2018/7/13
 */
@Service
public class AnalysisService extends BaseService{
    public Map<String,Object> business(Long shop_id,Integer day){
        return analysisDao.business(shop_id,day);
    }
    public Map<String,Object> customer(Long shop_id,Integer day){
        return analysisDao.customer(shop_id,day);
    }
    public Map<String,Object> evaluation(Long shop_id,Integer day){
        return analysisDao.evaluation(shop_id,day);
    }
    public Map<String,Object> groupBuy(Long shop_id,Integer day){
        return analysisDao.groupBuy(shop_id,day);
    }
    public Map<String,Object> trade(Long shop_id,Integer day){
        return analysisDao.trade(shop_id,day);
    }
}
