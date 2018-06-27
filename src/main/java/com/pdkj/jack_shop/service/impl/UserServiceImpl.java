package com.pdkj.jack_shop.service.impl;

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
        String key = "user" + token;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            User user = operations.get(key);
            System.out.println("有缓存"+user.getState());
            return user;
        }

        // 从 DB 中获取城市信息
        User user = userMapper.getUserByToken(token);

        // 插入缓存
        operations.set(key, user, 10, TimeUnit.SECONDS);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + user.toString());

        return user;
    }

}
