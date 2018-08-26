package com.pdkj.jack_shop.service;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class UserWalletService extends BaseService<Banner> {

    public Map<String, Object> getWallet(Long user_id) {
        return userWalletDao.getWallet(user_id);
    }

}
