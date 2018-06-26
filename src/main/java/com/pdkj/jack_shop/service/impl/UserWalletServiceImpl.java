package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.UserWalletMapper;
import com.pdkj.jack_shop.model.UserWallet;
import com.pdkj.jack_shop.service.UserWalletService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class UserWalletServiceImpl extends AbstractService<UserWallet> implements UserWalletService {
    @Resource
    private UserWalletMapper userWalletMapper;

}
