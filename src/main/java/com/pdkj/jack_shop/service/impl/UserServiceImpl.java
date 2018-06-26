package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.UserMapper;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.service.UserService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
