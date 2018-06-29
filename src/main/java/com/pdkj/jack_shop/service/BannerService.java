package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.model.ShopType;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class BannerService extends BaseService<Banner> {

    public List<Map<String, Object>> getAllBanner() {
        List<Map<String, Object>> shop = bannerDao.getAllBanner();
        return shop;
    }

}
