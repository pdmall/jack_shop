package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.GoodsType;
import com.pdkj.jack_shop.model.Photo;
import com.pdkj.jack_shop.model.Goods;
import com.pdkj.jack_shop.util.Tools;
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
    public List<Map<String, Object>> getShopGoods(Long shop_id) {
        return goodsDao.getShopGoods(shop_id);
    }
    public Long addGoods(Goods goods,Long user_id) {
        Photo photo = new Photo();
        photo.setId(Tools.generatorId());
        photo.setImg_url(goods.getImg_url());
        photo.setShop_id(goods.getShop_id());
        photo.setUser_id(user_id);
        photoDao.addPhoto(photo);
        return goodsDao.addGoods(goods);
    }
    public List<Map<String, Object>> getGoodsUnit() {
        return goodsDao.getGoodsUnit();
    }

    public List<Map<String, Object>> getGoodsType(Long shop_id) {
        return goodsDao.getGoodsType(shop_id);
    }

    public Long addGoodsType(GoodsType goodsType){
        goodsType.setId(Tools.generatorId());
        return goodsDao.addGoodsType(goodsType);
    }

}
