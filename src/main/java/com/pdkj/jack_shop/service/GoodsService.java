package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.Goods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class GoodsService extends BaseService<Goods> {
    public List<Map<String, Object>> getGroupBuyGoods(Long group_buy_id) {
        return goodsDao.getGroupBuyGoods(group_buy_id);
    }
    public List<Map<String, Object>> getShopGoods(Long group_buy_id) {
        return goodsDao.getShopGoods(group_buy_id);
    }
    public Long addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

}
