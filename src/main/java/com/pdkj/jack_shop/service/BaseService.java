package com.pdkj.jack_shop.service;

import com.pdkj.jack_shop.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class BaseService<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    RedisTemplate redisTemplate;

    @Resource
    UserDao userDao;

    @Resource
    ShopDao shopDao;

    @Resource
    ShopTypeDao shopTypeDao;

    @Resource
    CouponDao couponDao;
    @Resource
    BannerDao bannerDao;

    @Resource
    UserOrderDao userOrderDao;

    @Resource
    UserWalletDao userWalletDao;

    @Resource
    SignInDao signInDao;

    @Resource
    WxService wxService;
    @Resource
    LabelDao labelDao;
    @Resource
    ShopCommentDao shopCommentDao;
    @Resource
    GoodsDao goodsDao;
    @Resource
    GroupBuyDao groupBuyDao;
    @Resource
    UserConcernDao userConcernDao;
    @Resource
    PhotoDao photoDao;
    @Resource
    AnalysisDao analysisDao;
    @Resource
    QRCodeDao qrCodeDao;
    @Resource
    ParameterDao parameterDao;
    @Resource
    ShareOrginDao shareOrginDao;
    @Resource
    FlowMoneyDao flowMoneyDao;
    public Object getCache(String key) {
        Object value = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            value = operations.get(key);
        }
        return value;
    }

    public void setCache(String key, Object value, int seconds) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void setCache(String key, Object value) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, 10, TimeUnit.SECONDS);
    }

}
