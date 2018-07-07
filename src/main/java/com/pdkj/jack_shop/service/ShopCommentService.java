package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.dao.ShopCommentDao;
import com.pdkj.jack_shop.model.ShopComment;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class ShopCommentService extends BaseService<ShopComment> {

    public List<Map<String,Object>> getCommentList(Long shopId) {
        return shopCommentDao.getCommentList(shopId);
    }
}
