package com.pdkj.jack_shop.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.pdkj.jack_shop.configurer.AliYunSMS;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.core.Result;
import com.pdkj.jack_shop.core.ResultGenerator;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.util.Tools;
import org.hibernate.annotations.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Created by CodeGenerator on 2018/06/26.
 */

@Service
public class UserService extends BaseService<User> {

    @Cacheable(value = "token", key = "#p0")
    public User getUserByToken(String token) {
        User user = userDao.getUserByToken(token);
        return user;
    }


    public String getVerCode(String phone) throws CustomException, ClientException {
            String verCodeNum = getVerCodeNum(6);
            SendSmsResponse sendSmsResponse = AliYunSMS.sendSms(phone, verCodeNum);
            if(!sendSmsResponse.getCode().equalsIgnoreCase("ok")){
                if(sendSmsResponse.getMessage().contains("天")){
                    throw new CustomException("太快了，一天之后再试");
                }else if(sendSmsResponse.getMessage().contains("小时")){
                    throw new CustomException("太快了，一小时之后再试");
                }else if(sendSmsResponse.getMessage().contains("分钟")){
                    throw new CustomException("太快了，一分钟之后再试");
                }

            }
            setCache("verCode" + phone, verCodeNum, 300);
            return sendSmsResponse.getMessage();
    }

    public User register(User user, String verCode) throws CustomException {
        String oldCode = (String) getCache("verCode" + user.getPhone());
        if (oldCode.equals(verCode)) {
            User oldUser = userDao.getUserByPhone(user.getPhone());
            if(oldUser==null){
                user.setToken(Tools.uuid());
                userDao.save(user);
                user.setPassword(null);
                return user;
            }else{
                oldUser.setToken(Tools.uuid());
                userDao.update(oldUser);
                oldUser.setPassword(null);
                return oldUser;
            }
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

    public Map<String,Object> getUser( Long id){
        return  userDao.getUser(id);
    }


    public List<Map<String,Object>> getLevel2ByLevel3(String token) {

        return  userDao.getLevel2ByLevel3(getUserByToken(token).getId());
    }

    public List<Map<String,Object>> getLevel1ByLevel2(String token) {
         return  userDao.getLevel1ByLevel2(getUserByToken(token).getId());
    }
}
