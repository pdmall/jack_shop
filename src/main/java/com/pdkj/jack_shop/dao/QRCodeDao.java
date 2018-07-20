package com.pdkj.jack_shop.dao;
/**
 * @Project: jack_shop
 * @Package com.pdkj.jack_shop.dao
 * @author lvchong
 * @date 2018/7/20 17:32
 * @Copyright: 2018 www.zyht.com Inc. All rights reserved.
 * @version V1.0
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pdkj.jack_shop.util.NetUtils;
import com.pdkj.jack_shop.util.sql.MySql;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.pdkj.jack_shop.configurer.AliYunOSS.addFile;

/**
 * @author lvchong
 * @ClassName QRCodeDao
 * @Description 类描述
 * @date 2018/7/20
 */
@Repository
public class QRCodeDao extends DaoBase {

    String APPID = "wx9d82360ba0304046";
    String SECRET = "cef00252dbb5c92677f456be9bbb26ff";
    String page ="pages/index/index";


    public String addQRCode(String phone) throws Exception {
        String token = getWxToken();   // 得到token
        Map<String, Object> params = new HashMap<>();
        params.put("scene", phone);  //参数
        params.put("page", page); //位置
        params.put("width", 220);
        params.put("is_hyaline", true);

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+token);  // 接口
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        String body = JSON.toJSONString(params);           //必须是json模式的 post
        StringEntity entity;
        entity = new StringEntity(body);
        entity.setContentType("image/png");
        httpPost.setEntity(entity);
        HttpResponse response;
        response = httpClient.execute(httpPost);
        //String s = EntityUtils.toString(response.getEntity(), "utf-8");
        //System.out.println(s);
        InputStream inputStream = response.getEntity().getContent();
        //String name = phone+".png";
         //保存图片
        return  addFile(inputStream,phone);
    }


    /*
     * 获取 token
     * 普通的 get 可获 token
     */
    public String getWxToken() {
        try {
            Map<String, String> map = new LinkedHashMap<String, String>();
            map.put("grant_type", "client_credential");
            map.put("appid", APPID);
            map.put("secret", SECRET);

            String rt = NetUtils.httpGet("https://api.weixin.qq.com/cgi-bin/token", map);

            System.out.println(rt);
            JSONObject json = JSONObject.parseObject(rt);

            if (json.getString("access_token") != null || json.getString("access_token") != "") {
                return json.getString("access_token");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 将二进制转换成文件保存
     * @param instreams 二进制流
     * @param imgPath 图片的保存路径
     * @param imgName 图片的名称
     * @return
     *      1：保存正常
     *      0：保存失败
     */
    public static int saveToImgByInputStream(InputStream instreams,String imgPath,String imgName){
        int stateInt = 1;
        if(instreams != null){
            try {
                File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file);
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }

    public Map<String,Object> getQRCode(Long id) {
        MySql sql = new MySql();
        sql.append("SELECT qr_code FROM `user` where id = ?",id);
        return jdbcTemplate.queryForMap(sql.toString(),sql.getValues());
    }
}
