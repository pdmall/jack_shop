package com.pdkj.jack_shop.service.impl;

import com.pdkj.jack_shop.dao.UserConcernMapper;
import com.pdkj.jack_shop.model.UserConcern;
import com.pdkj.jack_shop.service.UserConcernService;
import com.pdkj.jack_shop.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class UserConcernServiceImpl extends AbstractService<UserConcern> implements UserConcernService {
    @Resource
    private UserConcernMapper userConcernMapper;

}
