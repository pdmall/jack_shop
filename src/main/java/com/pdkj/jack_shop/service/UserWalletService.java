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

    public List<Map<String, Object>> getWallet(Long user_id) {
        List<Map<String, Object>> wallet = userWalletDao.getWallet(user_id);
        return wallet;
    }

}
