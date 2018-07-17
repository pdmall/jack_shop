package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.IsPassGroupBuy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class GroupBuyService extends BaseService<IsPassGroupBuy> {

    // 添加套餐卷
    @Transactional
    public Long addGroupBuy(IsPassGroupBuy groupBuy, String goods_ids) {
        Long group_buy_id = groupBuyDao.addGroupBuy(groupBuy);
        groupBuyDao.addGroupBuyGoods(group_buy_id,goods_ids);
        return group_buy_id;
    }

    public List<Map<String, Object>> getGroupBuyByShopId(Long shopId , Integer coupon_state) {
        return groupBuyDao.getGroupBuyByShopId(shopId,coupon_state);
    }

    public Map<String, Object> getGroupBuyById(Long id) {
        return groupBuyDao.getGroupBuyById(id);
    }

    public List<Map<String, Object>> getGroupBuyByUserId(Long userId, Integer coupon_state) {
        return null; /*groupBuyDao.getGroupBuyByUserId(userId,coupon_state);*/
    }
    public List<Map<String,Object>> getLog(Long id){
        return groupBuyDao.getLog(id);
    }
    public List<Map<String, Object>> getIsPassGroupBuyList(Integer state ,Long shop_id) {
        return groupBuyDao.getIsPassGroupBuyList(state,shop_id);
    }


}
