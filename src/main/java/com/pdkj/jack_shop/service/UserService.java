package com.pdkj.jack_shop.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.pdkj.jack_shop.configurer.AliYunSMS;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.model.User;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class UserService extends BaseService<User> {

    @Cacheable(value = "token", key = "#p0")
    @PostMapping("getUserByToken")
    public User getUserByToken(String token) {
        User user = userDao.getUserByToken(token);
        return user;
    }

    @PostMapping("getVerCode")
    public String getVerCode(String phone) throws CustomException, ClientException {
        boolean exist = userDao.phoneHasExist(phone);
        if (!exist) {//不存在发送注册验证码
            String verCodeNum = getVerCodeNum(6);
            SendSmsResponse sendSmsResponse = AliYunSMS.sendSms(phone, verCodeNum);
            setCache("verCode" + phone, verCodeNum, 300);
            return sendSmsResponse.getMessage();
        }
        throw new CustomException("号码已存在");
    }
    @PostMapping("register")
    public void register(User user, String verCode) throws CustomException {
        String oldCode = (String) getCache("verCode" + user.getPhone());
        if (oldCode.equals(verCode)) {
            userDao.save(user);
        } else {
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
