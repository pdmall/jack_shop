package com.pdkj.jack_shop.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pdkj.jack_shop.configurer.XCXInfo;
import com.pdkj.jack_shop.core.CustomException;
import com.pdkj.jack_shop.model.User;
import com.pdkj.jack_shop.util.NetUtils;
import com.pdkj.jack_shop.util.Tools;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxService extends BaseService {

    /**
     *
     * @param code
     * @return
     * @throws Exception
     */
    public String getOpenId(String code) throws Exception {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("appid", XCXInfo.APPID));
        params.add(new BasicNameValuePair("secret", XCXInfo.APPSECRET));
        params.add(new BasicNameValuePair("js_code", code));
        params.add(new BasicNameValuePair("grant_type", XCXInfo.AUTHORIZATION_CODE));
        String content = NetUtils.post(params, XCXInfo.URL_OPENID);
        // 如果请求成功
        if (content == null) throw new Exception("获取openId失败");
        JSONObject jsonObject = (JSONObject) JSON.parse(content);
//        map.put("openid", jsonObject.getString("openid"));
//        map.put("session_key", jsonObject.getString("session_key"));
        return  jsonObject.getString("openid");
    }


    public User getUserByOpenId(String openid) throws Exception {
        User user = userDao.getUserByOpenId(openid);
        if (user!=null&&StringUtils.isNotEmpty(user.getToken())) {
            String token = Tools.uuid();
            userDao.updateToken(user.getId(), token);
            user.setToken(token);
        }
        return user;
    }


}
