package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.core.app_constant;
import com.pdkj.jack_shop.model.FlowMoney;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.model.UserOrder;
import com.pdkj.jack_shop.util.Tools;
import com.pdkj.jack_shop.util.sql.Pager;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class ShareOrginService extends BaseService {
    public List<Map<String,Object>> getMyLevel1(Long id,Pager pager) {
        return shareOrginDao.getMyLevel1(id,pager);
    }
    public List<Map<String,Object>> getMyLevel2(Long id ,Pager pager) {
        return shareOrginDao.getMyLevel2(id,pager);
    }
    public Map<String,Object> getMyLevel1Money(Long id) {
        return shareOrginDao.getMyLevel1Money(id);
    }
    public Map<String,Object> getMyLevel2Money(Long id) {
        return shareOrginDao.getMyLevel2Money(id);
    }
    public Map<String,Object> getMyAll(Long id) {
        return shareOrginDao.getMyAll(id);
    }

    public List<Map<String,Object>> getShareOrgin(Long user_id) {
        return shareOrginDao.getShareOrgin(user_id,4);
    }
    public Map<String,Object> getShareOrginSum(Long user_id) {
        return shareOrginDao.getShareOrginSum(user_id,4);
    }

    public Integer addShareOrgin(Long id, String phoneF) {
        Long level2 = userDao.getUserByPhone(phoneF).getId();
        Long level3 = shareOrginDao.getMyLevel2Id(level2);
        UserOrder userOrder = new UserOrder();
        userOrder.setOrder_state_id(4);
        Long order_id =  userOrderDao.addOrder(userOrder);
        //给一级奖励
        transferParent(id,order_id,appConstantDao.getPriceConstant(app_constant.ONE_LEVEL_SHARING));
        //给二级奖励
        transferParent(level2,order_id,appConstantDao.getPriceConstant(app_constant.TWO_LEVEL_SHARING));
        //给三级奖励
        if(level3!=null)
        transferParent(level3,order_id,appConstantDao.getPriceConstant(app_constant.THREE_LEVEL_SHARING));
        return shareOrginDao.addShareOrgin(id,level2,level3,appConstantDao.getPriceConstant(app_constant.ONE_LEVEL_SHARING));
    }

    private void transferParent(Long parent_id,Long order_id,  Object price) {
        //修改用户钱包余额 0 收入 1 支出
        userWalletDao.updateMoney(price, parent_id, 0);
        //添加流水记录
        FlowMoney flowMoney = new FlowMoney();
        flowMoney.setId(Tools.generatorId());
        flowMoney.setUser_id(parent_id);
        flowMoney.setValue(Double.valueOf(price.toString()));
        flowMoney.setItem_id(order_id);
        flowMoney.setFlow_state_id(6);
        flowMoneyDao.addFlowMoney(flowMoney);
    }
}
