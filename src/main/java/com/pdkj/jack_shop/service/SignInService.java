package com.pdkj.jack_shop.service;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class SignInService extends BaseService<Banner> {

    public List<Map<String, Object>> getSignInByUserId() {
        List<Map<String, Object>> shop = signInDao.getSignInByUserId();
        return shop;
    }

}
