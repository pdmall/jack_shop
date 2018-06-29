package com.pdkj.jack_shop.util;



import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Timestamp;
import java.util.*;

public class Tools {

    public static Object priseTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        return DateUtils.parseCalendar2String(calendar, "yyyy-MM-dd HH:mm:ss");
    }

    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    public static Long generatorId() {
        return System.currentTimeMillis()+new Random().nextInt(999);
    }


    /**
     * 把后面的List<Map> 集合 并入前面的集合，如果map key 相同就putAll
     * @param list1    饥饿的大鱼
     * @param list2    被吃的小鱼
     * @param key
     */
    public static void mergeMap(List<Map<String, Object>> list1, List<Map<String, Object>> list2, String key){
        for (int i=0; i<list1.size(); i++){
            Map<String, Object> pItem = list1.get(i);
            Iterator<Map<String, Object>> iterator = list2.iterator();
            while (iterator.hasNext()){
                Map<String, Object> sItem = iterator.next();
                if(pItem.get(key).toString().equals(sItem.get(key).toString())){
                    pItem.putAll(sItem);
                    iterator.remove();
                    break;
                }
            }
        }
        list1.addAll(list2);
    }

    public static String encryptPass(String username, String pass) {
        return DigestUtils.sha1Hex(username + pass);//混合密钥md5
    }
}
