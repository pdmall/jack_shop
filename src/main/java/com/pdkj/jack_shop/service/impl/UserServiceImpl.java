package com.pdkj.jack_shop.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.pdkj.jack_shop.configurer.AliYunSMS;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.dao.UserMapper;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.service.UserService;
import com.pdkj.jack_shop.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * Created by CodeGenerator on 2018/06/26.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public User getUserByToken(String token) {
        String key = "token"+token;
        User user = (User) getCache(key);
        if(user==null){
            user = userMapper.getUserByToken(token);
        }
        setCache(key,user);
        return user;
    }

    @Override
    public boolean getVerCode(String phone) throws ClientException {
        User user = userMapper.getUserByPhone(phone);
        //不存在发送注册验证码
        if(user==null){
            String verCodeNum = getVerCodeNum(6);
            setCache("verCode"+phone,verCodeNum);
            AliYunSMS.sendSms(phone,verCodeNum);
        }
        return user==null;
    }

    @Override
    public void register(User user,String vercode) throws CustomException {
        String oldCode = (String) getCache("verCode" + user.getPhone());
        if(oldCode.equals(vercode)){
            save(user);
        }else{
            throw new CustomException("验证码有误");
        }
    }

    private String getVerCodeNum(int varCodelength) {
        int verCode = 0;
        if (varCodelength == 6) {
            verCode = 1000000 - new Random().nextInt(899999);
        } else {
            verCode = 10000 - new Random().nextInt(8999);
        }
        return String.valueOf(verCode);
    }

}
