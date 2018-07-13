package com.pdkj.jack_shop.service;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.service
 * @author lvchong
 * @date 2018/7/13 10:34
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.pdkj.jack_shop.model.Album;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author lvchong
 * @ClassName AlbumService
 * @Description 类描述
 * @date 2018/7/13
 */
@Service
public class AlbumService extends BaseService<Album> {
    public List<Map<String,Object>> getUserPhoto(Long user_id){
        return albumDao.getUserPhoto(user_id);
    }
    public List<Map<String,Object>> getShopPhoto(Long shop_id){
        return albumDao.getShopPhoto(shop_id);
    }
    public List<Map<String,Object>> getGoodsPhoto(Long user_id,Long shop_id){
        return albumDao.getGoodsPhoto(user_id,shop_id);
    }
    public void addPhoto(Album album){
        albumDao.addPhoto(album);
    }
}
