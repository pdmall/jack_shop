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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
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
    public String getToken() throws Exception {
        List<NameValuePair> params = new ArrayList<>();
        Map<String,String> map = new HashMap<String,String>();
        map.put ("appid", XCXInfo.APPID);
        map.put ("secret", XCXInfo.APPSECRET);
        map.put("grant_type", XCXInfo.CLIENT_CREDENTIAL);
        String content = NetUtils.httpGet( XCXInfo.URL_ACCESS_TOKEN,map);

        // 如果请求成功
        if (content == null) throw new Exception("获取Token失败");
        JSONObject jsonObject = (JSONObject) JSON.parse(content);
        return  jsonObject.getString("access_token");
    }

/*    public String getminiqrQr(String scene,String page) throws Exception {
        String token = getToken();
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("scene",scene));
        params.add(new BasicNameValuePair("access_token",token));
        params.add(new BasicNameValuePair("page", page));
        params.add(new BasicNameValuePair("width", "430"));
        params.add(new BasicNameValuePair("auto_color", "false"));
        params.add(new BasicNameValuePair("line_color", "{\"r\":\"0\",\"g\":\"0\",\"b\":\"0\"}"));
        params.add(new BasicNameValuePair("is_hyaline","false"));

        String content = NetUtils.post(params, "https://api.weixin.qq.com/wxa/getwxacodeunlimit");
        // 如果请求成功
        if (content == null) throw new Exception("获取二维码失败失败");
        JSONObject jsonObject = (JSONObject) JSON.parse(content);
        return  jsonObject.getString("token");
    }*/

/*    public Map getQRCode(String sceneStr) throws Exception {
        String accessToken =getToken();
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken;
*//*            Map<String,Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", "pages/shop/getShopList");
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String,Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
            byte[] result = entity.getBody();
            *//*
            *//*inputStream = new ByteArrayInputStream(result);*//*

            *//*File file = new File("C:/Users/LVCHONG/Desktop/1.png");
            if (!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();*//*
        } catch (Exception e) {
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }*/

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
